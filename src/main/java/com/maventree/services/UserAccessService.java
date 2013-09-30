package com.maventree.services;

import java.util.Collection;

import com.maventree.entities.business_logic.Privilege;
import com.maventree.entities.business_logic.Role;
import com.maventree.entities.business_logic.User;
import com.maventree.services.util.navigation_service.RegistryTreeNode;

public interface UserAccessService {
	RegistryTreeNode getUserNavigation(User user);
	Collection<Privilege> getAllUserPrivileges(User exampleUser);
	Collection<Privilege> getUserPrivileges(User user);
	Collection<Privilege> getRolePrivileges(Role exampleRole);
	void addUser(User user);
	void addRole(Role role);
	void addPrivilege(Privilege privilege);
}
