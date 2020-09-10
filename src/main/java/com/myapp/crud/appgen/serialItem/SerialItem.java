package com.myapp.crud.appgen.serialItem;

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
import com.myapp.crud.appgen.partItem.PartItem;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Entity
@Table(name = "serialItem", catalog="case")
public class SerialItem {
	@Id
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SerialItem_ID_GEN")
    @SequenceGenerator(name = "SerialItem_ID_GEN", sequenceName = "SerialItem_ID_SEQ", allocationSize = 1)
    private Long id;

	
	private String name;
	
	private String description;
	@ManyToOne
	private PartItem partItem;
	
	private String status;

}


