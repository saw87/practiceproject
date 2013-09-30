package com.maventree.services;

import com.maventree.entities.business_logic.User;
import com.maventree.services.util.navigation_service.RegistryTreeNode;

public interface NavigationService {
	RegistryTreeNode prepareUserNavigationTree(User user);
}
