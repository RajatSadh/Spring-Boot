package com.demo.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
public class GenericDatabaseConfig {

	@Value("${hibernate.dialect}")
	private String dialect;
	
	@Value("${hibernate.show_sql}")
	private String showSql;
	
	@Value("${entitymanager.packageToScan}")
	private String hibernatePackageToScan;
	
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(HikariDataSource dataSource) {
		LocalSessionFactoryBean sessionFactoryBean =new LocalSessionFactoryBean();
		dataSource.setLeakDetectionThreshold(60*(long)1000);
		sessionFactoryBean.setDataSource(dataSource);
		String[] arr=hibernatePackageToScan.split(",");
		sessionFactoryBean.setPackagesToScan(arr);
		Properties hibernateProperties=new Properties();
		hibernateProperties.put("hibernate.dialect",dialect);
		hibernateProperties.put("hibernate.show_sql",showSql);
		sessionFactoryBean.setHibernateProperties(hibernateProperties);
		return sessionFactoryBean;
	}
	
	@Bean
	public HibernateTransactionManager transactionManager(HikariDataSource dataSource) {
		HibernateTransactionManager transactionManager=new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory(dataSource).getObject());
		return transactionManager;
	}
}
