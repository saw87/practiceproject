package com.maventree.services.impl.navigation_service;

import com.maventree.entities.business_logic.User;
import com.maventree.services.NavigationService;
import com.maventree.services.util.navigation_service.RegistryTreeNode;

public class DbNavigationService implements NavigationService {

	@Override
	public RegistryTreeNode prepareUserNavigationTree(User user) {
		RegistryTreeNode r = loadNavigationTree(user);
		
		// set all nodes in r to render  = true
		
		return null;
	}

	private RegistryTreeNode loadNavigationTree(User user) {
		// read navigation table from db and construct tree

		return null;
	}
	
}
