package com.myapp.crud.appgen.endUser;

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
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.myapp.crud.appgen.address.Address;
import com.myapp.crud.appgen.codeGroups.CodeGroups;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Entity
@Table(name = "endUser", catalog="case")
public class EndUser {
	@Id
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "EndUser_ID_GEN")
    @SequenceGenerator(name = "EndUser_ID_GEN", sequenceName = "EndUser_ID_SEQ", allocationSize = 1)
    private Long id;

	
	private String firstName;
	
	private String lastName;
	
	@JsonIgnore
	@OneToMany(mappedBy = "endUser")
	private List<Address> address;

}


