package com.tnourji.recrut.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("AD")
public class Admin extends User implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
   
    private Long id;
	

}
