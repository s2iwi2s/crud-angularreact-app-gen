package com.myapp.crud.appgen.endUser;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.myapp.crud.appgen.address.Address;
import com.myapp.crud.appgen.security.Role;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Entity
@Table(name = "endUser", uniqueConstraints = @UniqueConstraint(columnNames = { "userName" }, name = "endUser_PK"))
public class EndUser {
	@Id
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "EndUser_ID_GEN")
    @SequenceGenerator(name = "EndUser_ID_GEN", sequenceName = "EndUser_ID_SEQ", allocationSize = 1)
    private Long id;

	private String userName;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	@JsonIgnore
	@OneToMany(mappedBy = "endUser")
	private List<Address> address;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "endusers_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<Role> endUsersRoles;

}


