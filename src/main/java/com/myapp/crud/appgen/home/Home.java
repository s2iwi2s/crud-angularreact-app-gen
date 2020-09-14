package com.myapp.crud.appgen.home;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.myapp.crud.appgen.codeGroups.CodeGroups;

import java.util.Date;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Entity
@Table(name = "home")
public class Home {
	@Id
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "Home_ID_GEN")
    @SequenceGenerator(name = "Home_ID_GEN", sequenceName = "Home_ID_SEQ", allocationSize=1)
    private Long id;

	
	private String urlStr;
	
	private String name;

	public Home() {
	}

	public Home(String urlStr, String name) {
		super();
		this.urlStr = urlStr;
		this.name = name;
	}
	
	
}


