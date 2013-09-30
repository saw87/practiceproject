package com.maventree.services.impl.user_access_service;

import java.util.Collection;

import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.maventree.entities.business_logic.Privilege;
import com.maventree.entities.business_logic.Role;
import com.maventree.entities.business_logic.User;
import com.maventree.services.NavigationService;
import com.maventree.services.UserAccessService;
import com.maventree.services.impl.navigation_service.XmlNavigationService;
import com.maventree.services.util.navigation_service.RegistryTreeNode;
import com.maventree.util.HUtil;

public class DefaultUserAccessService implements UserAccessService {

	NavigationService navigationService = new XmlNavigationService();

	public RegistryTreeNode getUserNavigation(User user) {
		return navigationService.prepareUserNavigationTree(user);
	}

	@Override
	public Collection<Privilege> getAllUserPrivileges(User user) {
		Session session = HUtil.getOpenSession();

		User u = (User) session.createCriteria(User.class)
							   .add(Restrictions.eq("username", user.getUsername()))
							   .setFetchMode("privileges", FetchMode.JOIN)
							   .setFetchMode("roles", FetchMode.JOIN)
							   .setFetchMode("roles.privileges", FetchMode.JOIN)
							   .uniqueResult();

		Collection<Privilege> p = u.getPrivileges();
		for(Role r : u.getRoles())
			p.addAll(r.getPrivileges());
		
		// Query query = session.createSQLQuery(
		// "select GROUPS_PRIVILEGES.PRIVILEGES_ID as id from USERS_GROUPS join GROUPS_PRIVILEGES on USERS_GROUPS.GROUPS_ID = GROUPS_PRIVILEGES.GROUPS_ID where USERS_GROUPS.USERS_ID = :userId "
		// +
		// "union " +
		// "select PRIVILEGES_ID as id from USERS_PRIVILEGES where USERS_ID = :userId")
		// .setParameter("userId", user.getId());
		//
		// Collection<Integer> userPrivilegesIdList = query.list();

		// Collection<Privilege> allUserPrivileges =
		// session.createCriteria(Privilege.class)
		// .add(Restrictions.in("id", userPrivilegesIdList))
		// .list();
		//
		session.close();

		// return allUserPrivileges;
		return p;
	}

	@Override
	public Collection<Privilege> getUserPrivileges(User exampleUser) {
		Session session = HUtil.getOpenSession();

		Collection<Privilege> privileges = (Collection<Privilege>) session
				.createCriteria(User.class)
				.add(Restrictions.eq("id", exampleUser.getId()))
				.setProjection(Projections.property("privileges"));

		session.close();

		return privileges;
	}

	@Override
	public Collection<Privilege> getRolePrivileges(Role exampleRole) {
		Session session = HUtil.getOpenSession();

		Collection<Privilege> privileges = (Collection<Privilege>) session
				.createCriteria(Role.class)
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
