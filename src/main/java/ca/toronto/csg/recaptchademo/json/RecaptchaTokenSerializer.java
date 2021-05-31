package ca.toronto.csg.recaptchademo.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import java.io.IOException;

@JsonComponent
public class RecaptchaTokenSerializer extends JsonSerializer<RecaptchaToken> {

    @Override
    public void serialize(RecaptchaToken recaptchaToken, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("recaptcha_response", recaptchaToken.getRecaptchaResponse());
        jsonGenerator.writeEndObject();
    }
}
