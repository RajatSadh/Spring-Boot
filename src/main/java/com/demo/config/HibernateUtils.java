package com.demo.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HibernateUtils {

	@Autowired
	public SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		try {
			SessionFactory sessionFactory = getSessionFactory();
			Session session = null;
			session = sessionFactory.openSession();
			return session;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public Query createQuery(String hqlQuery) {
		Query query = null;
		Session session = getSession();
		query = session.createQuery(hqlQuery);
		return query;
	}

	public void closeSession(Session session) {
		if (session != null) {
			session.close();
		}
	}

}
