package com.maventree.services.util.navigation_service;

public class NavMenuItem implements Cloneable {
	String pAlias;
	boolean render;
	boolean hasPrivilege;
	String label;
	String resourceUrl;

	public String getpAlias() {
		return pAlias;
	}
	
	public boolean isRender() {
		return render;
	}
	
	public void setRender(boolean render) {
		this.render = render;
	}

	public boolean isHasPrivilege() {
		return hasPrivilege;
	}

	public void setHasPrivilege(boolean hasPrivilege) {
		this.hasPrivilege = hasPrivilege;
	}

	public void setpAlias(String pAlias) {
		this.pAlias = pAlias;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}



