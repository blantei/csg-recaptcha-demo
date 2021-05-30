package ca.toronto.csg.recaptchademo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
public class ApiController {

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
    
    @RequestMapping("/api/dosomething")
    public Map<String, String> doSomething() {
        Map<String, String> result = new HashMap<String, String>();
        String ip = null;
        try {
            ip = InetAddress.getLocalHost().toString();
        } catch (UnknownHostException e) {
            logger.error(e.toString());
        }
        result.put("server_ip", ip);
        result.put("server_date", new java.util.Date().toLocaleString());
        
        return result;
    }
}
