package ca.toronto.csg.recaptchademo.controller;

import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Value;
import com.fasterxml.jackson.databind.ObjectMapper;
import ca.toronto.csg.recaptchademo.exception.ForbiddenException;
import ca.toronto.csg.recaptchademo.json.RecaptchaToken;
import ca.toronto.csg.recaptchademo.json.RecaptchaValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class ApiController {

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Value("${csg.recaptcha.function_name}")
    private String recaptchaFunctionName;

    @Autowired
    private ObjectMapper objectMapper;
    
    @PostMapping("/dosomething")
    public Map<String, String> doSomething(@RequestHeader("x-cot-recaptcha-response") String recaptchaToken,
                                           HttpServletResponse response) {
        Map<String, String> result = new HashMap<String, String>();
        String ip = null;

        // verify ReCAPTCHA token
        Boolean isValid = recaptchaIsValid(recaptchaToken);

        if (!isValid) {
            throw new ForbiddenException();
        }
        
        try {
            ip = InetAddress.getLocalHost().toString();
        } catch (UnknownHostException e) {
            logger.error(e.toString());
        }
        result.put("server_ip", ip);
        result.put("server_date", new java.util.Date().toLocaleString());
        
        return result;
    }

    private Boolean recaptchaIsValid(String token) {

        RecaptchaToken recaptchaToken = new RecaptchaToken();
        recaptchaToken.setRecaptchaResponse(token);
        String jsonPayload = null;
        try {
                
            jsonPayload = objectMapper.writeValueAsString(recaptchaToken);
            logger.info(jsonPayload);
        } catch (Exception ex) {
            logger.error("Error converting to json", ex);
            return false;
        }
        AWSLambda client = AWSLambdaClientBuilder.standard().build();
        InvokeRequest request = new InvokeRequest()
            .withFunctionName(this.recaptchaFunctionName)
            .withPayload(jsonPayload);
        InvokeResult invokeResult = client.invoke(request);
        ByteBuffer buffer = invokeResult.getPayload();
        String validatorResponse = StandardCharsets.UTF_8.decode(buffer).toString();
        logger.info(validatorResponse);

        RecaptchaValidationResult validationResult = null;
        try {
            validationResult = objectMapper.readValue(validatorResponse, RecaptchaValidationResult.class);
            logger.info("Got validation result " + validationResult.getSuccess());
        } catch (Exception ex) {
            logger.error("Error converting from json", ex);
            return false;
        }
        return validationResult.getSuccess();
    }
}
