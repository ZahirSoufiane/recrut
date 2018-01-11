package com.tnourji.recrut.bean;

import java.io.Serializable;

/**
 * User Bean to handl Users proprties in forms or other. user have all proprties of Company and Condidate
 * 
 * @author Soufiane
 *
 */
public class UserBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private String password;
    
    private String phone;
    
    private String name;
    
    private String locality;
    
    private Integer nbEmployee;
    
    private String vatNumber;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getLocality() {
        return locality;
    }
    
    public void setLocality(String locality) {
        this.locality = locality;
    }
    
    public Integer getNbEmployee() {
        return nbEmployee;
    }
    
    public void setNbEmployee(Integer nbEmployee) {
        this.nbEmployee = nbEmployee;
    }
    
    public String getVatNumber() {
        return vatNumber;
    }
    
    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}
