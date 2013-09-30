package com.maventree.services.impl.navigation_service;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import org.jdom.JDOMException;

import com.maventree.entities.business_logic.Privilege;
import com.maventree.entities.business_logic.User;
import com.maventree.global.Registry;
import com.maventree.services.NavigationService;
import com.maventree.services.UserAccessService;
import com.maventree.services.impl.user_access_service.DefaultUserAccessService;
import com.maventree.services.util.navigation_service.NavMenuItem;
import com.maventree.services.util.navigation_service.NavigationTreeLoader;
import com.maventree.services.util.navigation_service.RegistryTreeNode;
import com.maventree.util.JsfUtil;

public class XmlNavigationService implements NavigationService {

	private UserAccessService userAccessService = Registry.getDefaultUserAccessService();
	private String path = JsfUtil.getDeploymentPath() + "\\WEB-INF\\classes\\navigation.xml"; // inject with spring
	
	@Override
	public RegistryTreeNode prepareUserNavigationTree(User user) {
		try {
			RegistryTreeNode newTreeRoot = new RegistryTreeNode(null);
			TT(null, loadNavigationTree(user), userAccessService.getAllUserPrivileges(user), newTreeRoot);
			return newTreeRoot;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	private RegistryTreeNode loadNavigationTree(User user) {
		try {
			NavigationTreeLoader navTreeLoader = new NavigationTreeLoader();
			return navTreeLoader.loadNavigationTree(path);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	private static void TT(RegistryTreeNode parent, RegistryTreeNode cur, Collection<Privilege> privileges, RegistryTreeNode parentOfNewNode) throws CloneNotSupportedException {
		
		// create a new node with the cloned data of current node	
		NavMenuItem copiedData = (NavMenuItem) ((NavMenuItem) cur.getData()).clone();
		RegistryTreeNode newNode = null;
		
		if(parent == null) {  // root node
			newNode = parentOfNewNode; // root node has already been created outside
			newNode.setData(copiedData);
		} else {
			newNode = new RegistryTreeNode(copiedData);
			newNode.setParent(parentOfNewNode);
			parentOfNewNode.addChild(newNode);
		}
		
		// if parent hasPrivilege -> set hasPrivilege and render
		// else checkPrivilege in collection // if true set hasPrivilege and
		// render and set parents' render
		if (parent != null && ((NavMenuItem) parentOfNewNode.getData()).isHasPrivilege()) {
			copiedData.setHasPrivilege(true);
			copiedData.setRender(true);
		} else if (containsPrivilegeAlias(privileges,  ((NavMenuItem) cur.getData()).getpAlias())) {
			copiedData.setHasPrivilege(true);
			copiedData.setRender(true);
			
			for(RegistryTreeNode r = parentOfNewNode; r != null ; r = r.getParent())
				((NavMenuItem) r.getData()).setRender(true);
		}

		for (RegistryTreeNode node : cur.getChildren()) {
			TT(cur, node, privileges, newNode);
		}
	}

	private static boolean containsPrivilegeAlias(Collection<Privilege> privileges, String pAlias) {
		Iterator<Privilege> itr = privileges.iterator();
		while (itr.hasNext()) {
			String alias = itr.next().getValue();
			if (pAlias.equals(alias))
				return true;
		}
		return false;
	}

}
