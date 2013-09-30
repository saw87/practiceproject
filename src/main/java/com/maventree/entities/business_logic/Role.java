package com.maventree.entities.business_logic;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="ROLES")
public class Role extends AbstractEntity {
	
	@ManyToMany
	@JoinTable(name = "ROLES_PRIVILEGES")
	private Set<Privilege> privileges = new HashSet<Privilege>();

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}
}
