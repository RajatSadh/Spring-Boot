package com.demo.dao.impl;

import java.math.BigInteger;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.type.BigIntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.config.HibernateUtils;
import com.demo.dao.GenericDAO;

@Transactional
@Repository
public class GenericDAOImpl implements GenericDAO {
	
	@Autowired
	HibernateUtils hibernateUtils;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public BigInteger getData() {
		Session session=hibernateUtils.getSession();
		BigInteger data;
		try {
//			String hqlQuery="select h.heirarchy as heirarchy from Heirarchy h where h.heirarchyId=:id";
//			Query query=session.createQuery(hqlQuery);
			String sqlQuery="select h.heirarchy as heirarchy from jum_heirarchy h where h.heirarchy_id=:id";
			Query query=session.createSQLQuery(sqlQuery).addScalar("heirarchy",new BigIntegerType());
			query.setParameter("id", Long.valueOf("1"));
			data=(BigInteger)query.uniqueResult();
			return data;
		}finally {
			hibernateUtils.closeSession(session);
		}
	}

}
