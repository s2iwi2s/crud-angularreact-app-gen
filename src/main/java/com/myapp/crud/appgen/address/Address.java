package com.myapp.crud.appgen.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.myapp.crud.appgen.codeGroups.CodeGroups;
import com.myapp.crud.appgen.endUser.EndUser;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Entity
@Table(name = "address", catalog="case")
public class Address {
	@Id
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "Address_ID_GEN")
    @SequenceGenerator(name = "Address_ID_GEN", sequenceName = "Address_ID_SEQ", allocationSize = 1)
    private Long id;

	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_ADDRESS_ENDUSER_ID"))
	private EndUser endUser;
	
	private String name;
	
	private String address1;
	
	private String address2;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private String zipCode;
	
	private String billTo;
	
	private String shipTo;

}


