package ca.toronto.csg.recaptchademo.json;

public class RecaptchaValidationResult {

    private Boolean success;

    private String hostname;
    
    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getHostname() {
        return this.hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
    
}
