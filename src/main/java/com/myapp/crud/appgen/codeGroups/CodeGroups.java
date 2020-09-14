package com.myapp.crud.appgen.codeGroups;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.myapp.crud.appgen.codeGroups.CodeGroups;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Entity
@Table(name = "codeGroups")
public class CodeGroups {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "CodeGroups_ID_GEN")
    @SequenceGenerator(name = "CodeGroups_ID_GEN", sequenceName = "CodeGroups_ID_SEQ", allocationSize=1)
    private Long id;

	
	private String code;
	
	private String value;
	
	private String description;
	
	private String bool;
	
	private String num;

}


