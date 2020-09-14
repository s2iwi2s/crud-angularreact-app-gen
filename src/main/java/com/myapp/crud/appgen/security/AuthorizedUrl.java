package com.myapp.crud.appgen.security;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
@Table(name = "AuthorizedUrl", uniqueConstraints = @UniqueConstraint(columnNames = {
		"url" }, name = "AuthorizedUrl_PK"))
public class AuthorizedUrl {
	@Transient
	private Log log = LogFactory.getLog(this.getClass().getCanonicalName());

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "AUTHORIZEDURL_GENERATOR")
	@SequenceGenerator(allocationSize = 1, name = "AUTHORIZEDURL_GENERATOR", sequenceName = "AUTHORIZEDURL_SEQ")
	private Long id;

	private String url;

	@org.hibernate.annotations.Type(type = "yes_no")
	@Column(columnDefinition = "char(1) default 'N'")
	private boolean permit;

	@org.hibernate.annotations.Type(type = "yes_no")
	@Column(columnDefinition = "char(1) default 'N'")
	private boolean methodGet;
	
	@org.hibernate.annotations.Type(type = "yes_no")
	@Column(columnDefinition = "char(1) default 'N'")
	private boolean methodPost;
	
	@org.hibernate.annotations.Type(type = "yes_no")
	@Column(columnDefinition = "char(1) default 'N'")
	private boolean methodDelete;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "AUTHORIZEDURLS_ROLES", joinColumns = @JoinColumn(name = "URL_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"))
	private Collection<Role> authorizedUrlRoles;

	public AuthorizedUrl(Long id, String url, boolean permit, Collection<Role> authorizedUrlRoles) {
		super();
		this.id = id;
		this.url = url;
		this.permit = permit;
		 this.authorizedUrlRoles = authorizedUrlRoles;
	}
	
	public String[] getRolesUrl() {
		String[] roles = {};
		if (roles != null) {
			Role[] rolesAry = {};
			rolesAry = this.authorizedUrlRoles.toArray(rolesAry);

			roles = new String[rolesAry.length];
			for (int i = 0; i < roles.length; i++) {
				String name = rolesAry[i].getName();
				roles[i] = name.substring(name.indexOf("_") + 1);
			}
		}
		return roles;
	}
}
