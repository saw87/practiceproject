package com.maventree.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class JsfUtil {
	public static String getContextPath() {
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return request.getRequestURL().toString().replace(request.getRequestURI().substring(0), "") + request.getContextPath();
	}
	
	public static String getDeploymentPath() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		return servletContext.getRealPath("/");
	}
	
	public static String getMavenDeploymentPath(String path) {
		return getDeploymentPath() + "\\WEB-INF\\classes\\" + path;
	}
}
