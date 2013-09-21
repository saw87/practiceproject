package com.maventree.entities.business_logic.user_access;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.maventree.entities.business_logic.AbstractEntity;


@Entity
@Table(name="ROLES")
public class Role extends AbstractEntity {
	
	@ManyToMany
	@JoinTable(name="ROLES_PRIVILEGES")
	private Collection<Privilege> privileges = new ArrayList<Privilege>();

	public Collection<Privilege> getRolePrivilegesList() {
		return privileges;
	}

	public void setRolePrivilegesList(Collection<Privilege> rolePrivilegesList) {
		this.privileges = rolePrivilegesList;
	}
	
	public void addPrivilege(Privilege p) {
		privileges.add(p);
	}
}
