package com.myapp.crud.appgen.security;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.myapp.crud.appgen.endUser.EndUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Role", uniqueConstraints = @UniqueConstraint(columnNames = { "name" }, name = "Role_PK"))
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ROLE_GENERATOR")
	@SequenceGenerator(allocationSize = 1, name = "ROLE_GENERATOR", sequenceName = "ROLE_SEQ")
	private Long id;

	private String name;
	@ManyToMany(mappedBy = "endUsersRoles")
	private Collection<EndUser> endUsers;

	@ManyToMany(mappedBy = "authorizedUrlRoles")
	private Collection<AuthorizedUrl> authorizedUrls;

}
