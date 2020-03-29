package com.demo.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Configuration
public class ConnectionLeakUtil {

    @Value("${hibernate.dialect}")
    private String dialect;

    @Autowired
    HibernateUtils hibernateUtils;

    private Logger logger = LogManager.getLogger(ConnectionLeakUtil.class);

    @Scheduled(fixedRate = 10000)
    public void DBIdleConnection(){
        Session session = null;
        try {
            session = hibernateUtils.getSession();
            String sqlQuery = "SELECT count(*),state FROM pg_stat_activity GROUP BY 2";
            Query query = session.createSQLQuery(sqlQuery);
            List<Map<String, Object>> result = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
            final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            logger.info("DBIdleConnection : {} ", result);
        }finally {
                hibernateUtils.closeSession(session);
        }
    }
}