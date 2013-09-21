package com.maventree.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Session;

import com.maventree.entities.business_logic.user_access.Group;
import com.maventree.entities.business_logic.user_access.Privilege;
import com.maventree.entities.business_logic.user_access.Role;
import com.maventree.entities.business_logic.user_access.User;
import com.maventree.services.UserAccessService;
import com.maventree.util.HUtil;

public class MockUserAccessService implements UserAccessService {
	
	@Override
	public Collection<Privilege> getAllUserPrivileges(User user) {
			
		ArrayList<Privilege> allUserPrivileges = new ArrayList<Privilege>();
		Privilege p1 = new Privilege();
		p1.setValue("p1");
		Privilege p2 = new Privilege();
		p2.setValue("p2");
		Privilege p5 = new Privilege();
		p5.setValue("p3");
		Privilege p7 = new Privilege();
		p7.setValue("all");
//		allUserPrivileges.add(p1);
//		allUserPrivileges.add(p2);
//		allUserPrivileges.add(p5);
		allUserPrivileges.add(p7);
		return allUserPrivileges;
	}

	@Override
	public Collection<Privilege> getUserPrivileges(User exampleUser) {
		return null;
	}
	
	@Override
	public Collection<Privilege> getGroupPrivileges(Group exampleGroup) {
		return null;
	}
	
	@Override
	public Collection<Privilege> getRolePrivileges(Role exampleRole) {
		return null;
	}

	@Override
	public void addUser(User user) {
		Session session = HUtil.getOpenSession();	
		session.beginTransaction();
		
		session.save(user);
		
		session.getTransaction().commit();
		session.close();
	}
	
	@Override
	public void addGroup(Group group) {
		Session session = HUtil.getOpenSession();	
		session.beginTransaction();
		
		session.save(group);
		
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void addRole(Role role) {
		Session session = HUtil.getOpenSession();	
		session.beginTransaction();
		
		session.save(role);
		
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void addPrivilege(Privilege privilege) {
		Session session = HUtil.getOpenSession();	
		session.beginTransaction();
		
		session.save(privilege);
		
		session.getTransaction().commit();
		session.close();
	}
	
}
