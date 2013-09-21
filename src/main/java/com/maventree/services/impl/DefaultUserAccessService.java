package com.maventree.services.impl;

import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.maventree.entities.business_logic.user_access.Group;
import com.maventree.entities.business_logic.user_access.Privilege;
import com.maventree.entities.business_logic.user_access.Role;
import com.maventree.entities.business_logic.user_access.User;
import com.maventree.services.UserAccessService;
import com.maventree.util.HUtil;

public class DefaultUserAccessService implements UserAccessService {
	
	@Override
	public Collection<Privilege> getAllUserPrivileges(User user) {
		Session session = HUtil.getOpenSession();
	
		Query query = session.createSQLQuery(
						"select GROUPS_PRIVILEGES.PRIVILEGES_ID as id from USERS_GROUPS join GROUPS_PRIVILEGES on USERS_GROUPS.GROUPS_ID = GROUPS_PRIVILEGES.GROUPS_ID where USERS_GROUPS.USERS_ID = :userId " +
						"union " +
						"select ROLES_PRIVILEGES.PRIVILEGES_ID as id from USERS_ROLES join ROLES_PRIVILEGES on USERS_ROLES.ROLES_ID = ROLES_PRIVILEGES.ROLES_ID where USERS_ROLES.USERS_ID = :userId " +
						"union " +
						"select PRIVILEGES_ID as id from USERS_PRIVILEGES where USERS_ID = :userId")
						.setParameter("userId", user.getId());
				
		Collection<Integer> userPrivilegesIdList = query.list();
		
		Collection<Privilege> allUserPrivileges = session.createCriteria(Privilege.class)
														 .add(Restrictions.in("id", userPrivilegesIdList))
														 .list();

		session.close();
		
		return allUserPrivileges;
	}

	@Override
	public Collection<Privilege> getUserPrivileges(User exampleUser) {
		Session session = HUtil.getOpenSession();
		
		Collection<Privilege> privileges = (Collection<Privilege>) 
				session.createCriteria(User.class)
				  	   .add(Restrictions.eq("id", exampleUser.getId()))
				   	   .setProjection(Projections.property("privileges"));
		
		session.close();
		
		return privileges;
	}
	
	@Override
	public Collection<Privilege> getGroupPrivileges(Group exampleGroup) {
		Session session = HUtil.getOpenSession();
		
		Collection<Privilege> privileges = (Collection<Privilege>) 
				session.createCriteria(Group.class)
					   .add(Restrictions.eq("id", exampleGroup.getId()))
					   .setProjection(Projections.property("privileges"));
		
		session.close();
		
		return privileges;
	}
	
	@Override
	public Collection<Privilege> getRolePrivileges(Role exampleRole) {
		Session session = HUtil.getOpenSession();
		
		Collection<Privilege> privileges = (Collection<Privilege>) 
				session.createCriteria(Role.class)
				.add(Restrictions.eq("id", exampleRole.getId()))
				.setProjection(Projections.property("privileges"));
		
		session.close();
		
		return privileges;
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
