package com.maventree.global;

import com.maventree.services.impl.user_access_service.DefaultUserAccessService;


public class Registry {
	public static DefaultUserAccessService d = new DefaultUserAccessService();
	
	public static DefaultUserAccessService getDefaultUserAccessService() {
	
		return d;
	}
	
}
