package com.myapp.crud.appgen.partItem;

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

import com.myapp.crud.appgen.codeGroups.CodeGroups;

import java.util.Date;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "partItem", catalog="case")
public class PartItem {
	@Id
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PartItem_ID_GEN")
    @SequenceGenerator(name = "PartItem_ID_GEN", sequenceName = "PartItem_ID_SEQ", allocationSize = 1)
    private Long id;

	
	private String name;
	
	private String description;
	
	private String serialized;

}


