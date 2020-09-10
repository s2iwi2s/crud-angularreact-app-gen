package com.myapp.crud.appgen.myCase;

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

import com.myapp.crud.appgen.codeGroups.CodeGroups;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "myCase", catalog="case")
public class MyCase {
	@Id
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "MyCase_ID_GEN")
    @SequenceGenerator(name = "MyCase_ID_GEN", sequenceName = "MyCase_ID_SEQ", allocationSize = 1)
    private Long id;

	
	private String title;
	@ManyToOne
	private CodeGroups status;
	@ManyToOne
	private CodeGroups caseType1;
	@ManyToOne
	private CodeGroups caseType2;
	@ManyToOne
	private CodeGroups caseType3;
	@ManyToOne
	private CodeGroups statusCode;
	
	private String comments;

}


