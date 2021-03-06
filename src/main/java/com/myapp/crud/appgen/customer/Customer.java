package com.myapp.crud.appgen.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.myapp.crud.appgen.codeGroups.CodeGroups;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {
	@Id
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "Customer_ID_GEN")
    @SequenceGenerator(name = "Customer_ID_GEN", sequenceName = "Customer_ID_SEQ", allocationSize = 1)
    private Long id;

	
	private String firstName;
	
	private String lastName;
	
	private String address1;
	
	private String address2;
	
	private String city;
	
	private String state;

}


