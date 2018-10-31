package com.ideas2it.dvdstore.sessionFactory;

import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ideas2it.dvdstore.dao.impl.DvdDaoImpl;
import com.ideas2it.dvdstore.logger.DvdLogger;



/** 
 * <p>
 * SessionFactoryManager class is used to provide Sessionfactory object
 * Singleton class is used in SessionFactoryManager class which is restrict user 
 * to create multiple objects
 * <p/>
 *
 * @author Muniraj M
 */
public class SessionFactoryManager {

    private static SessionFactoryManager sessionFactory;
    private DvdLogger dvdLogger = new DvdLogger();
    private SessionFactory factory;
    /** 
      * private constructor used to restrict to create object for outside class
      */
    private SessionFactoryManager() {
    }

    /**
      * <p>
      * getInstance method provide object ofor session factory
      * If the object is already created it returns same objected
      * If object is null then only create new object for this class
      * </p>
      *
      */
    public static SessionFactoryManager getInstance() {
        if (null == sessionFactory) {
            sessionFactory = new SessionFactoryManager();
        }
        return sessionFactory;
    }
    
    /** 
      * <p>
      * factory function provide session factory
      * factory method created by configuration object which will contains all 
      * Database related details
      * <p>
      */
    public SessionFactory factory() {
        try {
            if (null == factory) {
            Configuration configure = new Configuration();
            configure.configure("hibernate.cfg.xml");
             factory = configure.buildSessionFactory();
           }
            return factory;
        } catch(Throwable e) {
        dvdLogger.error("Error occured in Session",e);
    
        }
        return null;
    }

   public void closeSession(Session session) {
        try {
            if(null != session) {
                session.close();
            }
        } catch (HibernateException e) {
        }
    }
}

