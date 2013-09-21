package com.maventree.global;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import org.jdom.JDOMException;

import com.maventree.entities.business_logic.user_access.Privilege;
import com.maventree.global.registry_util.NavMenuItem;
import com.maventree.global.registry_util.NavigationTreeLoader;
import com.maventree.global.registry_util.RegistryTreeNode;
import com.maventree.util.JsfUtil;

public class Registry {

	private static RegistryTreeNode navigationTreeRoot;

	static {
		try {
			NavigationTreeLoader navTreeLoader = new NavigationTreeLoader();
			navigationTreeRoot = navTreeLoader.loadNavigationTree(JsfUtil.getMavenDeploymentPath("registry\\navigation.xml"));
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static RegistryTreeNode getUserNavigationTree(Collection<Privilege> privileges) {
		try {
			RegistryTreeNode newTreeRoot = new RegistryTreeNode(null);
			TT(null, navigationTreeRoot, privileges, newTreeRoot);
			return newTreeRoot;
		} catch (CloneNotSupportedException e) {
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
