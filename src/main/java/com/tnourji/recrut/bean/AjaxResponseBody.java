package com.tnourji.recrut.bean;

import org.codehaus.jackson.map.annotate.JsonView;

import com.tnourji.recrut.audit.json.Views;

/***
 * ajax response body
 * 
 * @author Michael Tnourji
 *         
 */
public class AjaxResponseBody {
    
    @JsonView(Views.Public.class)
    String msg;
    
    @JsonView(Views.Public.class)
    String code;
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
}