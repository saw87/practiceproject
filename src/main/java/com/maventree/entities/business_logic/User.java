package com.maventree.entities.business_logic;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
@AttributeOverride(name="value", column=@Column(name="real_name"))
public class User extends AbstractEntity {

	@Column(unique = true)
	private String username;

	@ManyToMany
	@JoinTable(name = "USERS_ROLES")
	private Set<Role> roles = new HashSet<Role>();

	@ManyToMany
	@JoinTable(name = "USERS_PRIVILEGES")
	private Set<Privilege> privileges = new HashSet<Privilege>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public Collection<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

	public void setUserPrivileges(Set<Privilege> userPrivileges) {
		this.privileges = userPrivileges;
	}
	
	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void addPrivilege(Privilege p) {
		privileges.add(p);
	}

	public void addRole(Role r) {
		roles.add(r);
	}
}
