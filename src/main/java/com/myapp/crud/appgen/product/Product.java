package com.myapp.crud.appgen.product;

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
@Table(name = "product")
public class Product {
	@Id
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "Product_ID_GEN")
    @SequenceGenerator(name = "Product_ID_GEN", sequenceName = "Product_ID_SEQ", allocationSize = 1)
    private Long id;

	
	private String itemCode;
	
	private String description;
	@ManyToOne
	private CodeGroups category;
	
	private String price;
	
	private String quantity;

}


