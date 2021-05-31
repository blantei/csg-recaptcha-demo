package ca.toronto.csg.recaptchademo.json;

//import org.springframework.boot.jackson.JsonComponent;

//@JsonComponent
public class RecaptchaToken {

    private String recaptchaResponse;

    public void setRecaptchaResponse(String recaptchaResponse) {
        this.recaptchaResponse = recaptchaResponse;
    }

    public String getRecaptchaResponse() {
        return this.recaptchaResponse;
    }
}
