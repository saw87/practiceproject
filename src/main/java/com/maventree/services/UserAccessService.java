package com.maventree.services;

import java.util.Collection;

import com.maventree.entities.business_logic.user_access.Group;
import com.maventree.entities.business_logic.user_access.Privilege;
import com.maventree.entities.business_logic.user_access.Role;
import com.maventree.entities.business_logic.user_access.User;

public interface UserAccessService {
	Collection<Privilege> getAllUserPrivileges(User exampleUser);
	Collection<Privilege> getUserPrivileges(User user);
	Collection<Privilege> getGroupPrivileges(Group exampleGroup);
	Collection<Privilege> getRolePrivileges(Role exampleRole);
	void addUser(User user);
	void addGroup(Group group);
	void addRole(Role role);
	void addPrivilege(Privilege privilege);
}
