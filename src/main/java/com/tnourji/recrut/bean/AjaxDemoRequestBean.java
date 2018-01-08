package com.tnourji.recrut.bean;

import org.codehaus.jackson.map.annotate.JsonView;

import com.tnourji.recrut.audit.json.Views;

/***
 * ajax demo Request Response bean
 * 
 * @author Michael Tnourji
 *
 */
public class AjaxDemoRequestBean {
    
    @JsonView(Views.Public.class)
    private Boolean status;
    
    @JsonView(Views.Public.class)
    private String errName;
    
    @JsonView(Views.Public.class)
    private String errHuman;
    
    @JsonView(Views.Public.class)
    private String errPhoto;
    
    @JsonView(Views.Public.class)
    private String errEmail;
    
    @JsonView(Views.Public.class)
    private String message;
    
    public Boolean getStatus() {
        return status;
    }
    
    public String getErrEmail() {
        return errEmail;
    }
    
    public void setErrEmail(String errEmail) {
        this.errEmail = errEmail;
    }
    
    public void setStatus(Boolean status) {
        this.status = status;
    }
    
    public String getErrName() {
        return errName;
    }
    
    public void setErrName(String errName) {
        this.errName = errName;
    }
    
    public String getErrHuman() {
        return errHuman;
    }
    
    public void setErrHuman(String errHuman) {
        this.errHuman = errHuman;
    }
    
    public String getErrPhoto() {
        return errPhoto;
    }
    
    public void setErrPhoto(String errPhoto) {
        this.errPhoto = errPhoto;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
}
