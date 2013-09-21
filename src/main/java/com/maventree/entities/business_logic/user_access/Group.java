package com.maventree.entities.business_logic.user_access;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.maventree.entities.business_logic.AbstractEntity;


@Entity
@Table(name = "GROUPS")
public class Group extends AbstractEntity {
	
	@ManyToMany
	@JoinTable(name = "GROUPS_PRIVILEGES")
	private Collection<Privilege> privileges = new ArrayList<Privilege>();

	public Collection<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivilegesList(Collection<Privilege> groupPrivilegesList) {
		this.privileges = groupPrivilegesList;
	}
	
	public void addPrivilege(Privilege p) {
		privileges.add(p);
	}
}
