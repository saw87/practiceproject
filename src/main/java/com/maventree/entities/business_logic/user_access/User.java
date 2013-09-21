package com.maventree.entities.business_logic.user_access;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.maventree.entities.business_logic.AbstractEntity;

@Entity
@Table(name = "USERS")
public class User extends AbstractEntity {

	@Column(name = "USERNAME")
	private String username;

	@ManyToMany
	@JoinTable(name = "USERS_ROLES")
	private Collection<Role> roles = new ArrayList<Role>();

	@ManyToMany
	@JoinTable(name = "USERS_GROUPS")
	private Collection<Group> groups = new ArrayList<Group>();

	@ManyToMany
	@JoinTable(name = "USERS_PRIVILEGES")
	private Collection<Privilege> privileges = new ArrayList<Privilege>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public Collection<Group> getGroups() {
		return groups;
	}

	public void setGroups(Collection<Group> groups) {
		this.groups = groups;
	}

	public Collection<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Collection<Privilege> privileges) {
		this.privileges = privileges;
	}

	public void setUserPrivileges(Collection<Privilege> userPrivileges) {
		this.privileges = userPrivileges;
	}

	public void addPrivilege(Privilege p) {
		privileges.add(p);
	}

	public void addRole(Role r) {
		roles.add(r);
	}

	public void addGroup(Group g) {
		groups.add(g);
	}
}
