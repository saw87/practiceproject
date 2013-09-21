package com.maventree.controllers;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.maventree.entities.business_logic.user_access.Privilege;
import com.maventree.entities.business_logic.user_access.User;
import com.maventree.global.Registry;
import com.maventree.global.registry_util.NavMenuItem;
import com.maventree.global.registry_util.RegistryTreeNode;
import com.maventree.services.UserAccessService;
import com.maventree.services.impl.MockUserAccessService;

@ManagedBean
@SessionScoped
public class UserAccessBean {

	private UserAccessService userAccessService = new MockUserAccessService();
	
	private TreeNode root;
	private TreeNode selectedNode;
	private String page;

	public String login(String username) {
		User u = new User();
		u.setUsername(username);
		
		Collection<Privilege> privileges = userAccessService.getAllUserPrivileges(u);
		RegistryTreeNode userNavigationTreeRoot = Registry.getUserNavigationTree(privileges);
		buildTreeMenu(userNavigationTreeRoot);
		return "main";
	}
	
	private void buildTreeMenu(RegistryTreeNode registryTreeNodeRoot) {
		buildTreeMenuTT(null, registryTreeNodeRoot, null);
	}
	
	private void buildTreeMenuTT(RegistryTreeNode parent, RegistryTreeNode cur, TreeNode previousNewNode) {
		NavMenuItem curData = (NavMenuItem) cur.getData();
		
		if(curData.isRender() == false)
			return;

		if(parent == null) {
			root = new DefaultTreeNode("ROOT", null);
		
			for (RegistryTreeNode n : cur.getChildren()) 
				buildTreeMenuTT(cur, n, root);
		}
		else {
			TreeNode newNode = new DefaultTreeNode(curData, previousNewNode);
			
			if(curData.getResourceUrl() != null)
				newNode.setSelectable(true); // menu item
			else
				newNode.setExpanded(true);	// sub menu or root
			
			for (RegistryTreeNode n : cur.getChildren()) 
				buildTreeMenuTT(cur, n, newNode);
		}	
	}

	public String logout() {
		return "index";
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public void onNodeSelect(NodeSelectEvent event) {
		page = (String) ((NavMenuItem)event.getTreeNode().getData()).getResourceUrl();
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
	
	
	// public void addMessage(String summary) {
	// FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
	// summary, null);
	// FacesContext.getCurrentInstance().addMessage(null, message);
	// }
}
