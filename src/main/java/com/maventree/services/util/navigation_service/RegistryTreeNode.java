package com.maventree.services.util.navigation_service;

import java.util.ArrayList;
import java.util.List;

public class RegistryTreeNode {
	private Object data;
	private RegistryTreeNode parent;
	private List<RegistryTreeNode> children;

	public RegistryTreeNode(Object rootData) {
		data = rootData;
		children = new ArrayList<RegistryTreeNode>();
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public RegistryTreeNode getParent() {
		return parent;
	}

	public void setParent(RegistryTreeNode parent) {
		this.parent = parent;
	}

	public List<RegistryTreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<RegistryTreeNode> children) {
		this.children = children;
	}

	public void addChild(RegistryTreeNode n) {
		n.setParent(this);
		children.add(n);
	}

}