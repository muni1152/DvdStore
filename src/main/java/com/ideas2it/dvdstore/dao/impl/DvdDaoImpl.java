package com.ideas2it.dvdstore.dao.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.PersistenceException;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Criteria;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.apache.log4j.Logger;

import com.ideas2it.dvdstore.common.DvdConstants;
import com.ideas2it.dvdstore.dao.DvdDao;
import com.ideas2it.dvdstore.exception.DvdException;
import com.ideas2it.dvdstore.logger.DvdLogger;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.sessionFactory.SessionFactoryManager;

/** 
  * <p>
  * List actions are performed at the Dao
  * It performs specific operation depend on the user request
  * <p/>
  * 
  * author Muniraj M
  */
public class DvdDaoImpl implements DvdDao {

    private SessionFactoryManager sessionFactory;
    private DvdLogger dvdLogger = new DvdLogger();

    /**
      * @{inheritdoc}
      */
    public Boolean insertDvd(Dvd dvd) throws DvdException {
        Session session = null;
        SessionFactory factory;
        Transaction transaction = null;
        try{
            sessionFactory = SessionFactoryManager.getInstance();
            factory = sessionFactory.factory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            dvd.setStatus(DvdConstants.LABEL_YES);
            session.save(dvd);
            transaction.commit();
            return Boolean.TRUE;
        } catch (ConstraintViolationException e) {
            throw new DvdException(DvdConstants.MESSAGE_DUPLICATE + 
                "  For Dvd Name =  " + dvd.getName() + "   ReleaseDate = " +
                dvd.getReleaseDate() + "  Language = " + dvd.getLanguage());
        } catch (HibernateException e) {
            dvdLogger.error(DvdConstants.MESSAGE_EXCEPTION_CREATE + 
                 dvd.getName(), e);
            if(null != transaction) {
                transaction.rollback();
            }
            throw new DvdException(DvdConstants.MESSAGE_EXCEPTION_CREATE + 
                dvd.getName());
        } finally {
            session.close();
        }
    }
 
     /**
      * @{inheritdoc}
      */
    public Boolean deleteDvd(Dvd dvd) throws DvdException {
        Session session = null;
        SessionFactory factory;
        Transaction transaction = null;
        try{
            sessionFactory = SessionFactoryManager.getInstance();
            factory = sessionFactory.factory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            dvd.setStatus(DvdConstants.LABEL_NO);
            session.update(dvd);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            dvdLogger.error(DvdConstants.MESSAGE_EXCEPTION_DELETE + 
                dvd.getId(), e);
            if(null != transaction) {
                transaction.rollback();
            }
            throw new DvdException(DvdConstants.MESSAGE_EXCEPTION_DELETE + 
                dvd.getId());
        } finally {
            session.close();
        }
    }
    
    /**
      * @{inheritdoc}
      */
    public Dvd searchDvd(int dvdId) throws DvdException {
        Session session = null;
        SessionFactory factory;
        try {
            sessionFactory = SessionFactoryManager.getInstance();
            factory = sessionFactory.factory();
            session = factory.openSession();
            return (Dvd) session.get(Dvd.class,dvdId );
        } catch (HibernateException e) {
            dvdLogger.error(DvdConstants.MESSAGE_EXCEPTION_SEARCH + dvdId, e);
            throw new DvdException(DvdConstants.MESSAGE_EXCEPTION_SEARCH + dvdId);
        } finally {
            session.close();
        }
    }

    /**
      * @{inheritdoc}
      */
    public Boolean updateDvd(Dvd dvd) throws DvdException {
        Session session = null;
        SessionFactory factory;
        Transaction transaction = null;
        try{
            sessionFactory = SessionFactoryManager.getInstance();
            factory = sessionFactory.factory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.update(dvd);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            dvdLogger.error(DvdConstants.MESSAGE_EXCEPTION_UPDATE +
                dvd.getId(),e);
            if(null != transaction) {
                transaction.rollback();
            }
            throw new DvdException(DvdConstants.MESSAGE_EXCEPTION_UPDATE + 
                dvd.getId());
        } catch (PersistenceException e) {
            dvdLogger.error(DvdConstants.MESSAGE_DUPLICATE + 
                "  For Dvd Name =  " + dvd.getName() + "   ReleaseDate = " +
                dvd.getReleaseDate() + "  Language = " + dvd.getLanguage(), e);
            throw new DvdException(DvdConstants.MESSAGE_DUPLICATE + 
                "  For Dvd Name =  " + dvd.getName() + "   ReleaseDate = " +
                dvd.getReleaseDate() + "  Language = " + dvd.getLanguage());
        } finally {
            session.close();
        }
    }
    
    /**
      * @{inheritdoc}
      */
    public List<Dvd> getDvds(String status) throws DvdException {

        Session session = null;
        SessionFactory factory;
        Transaction transaction = null;
        try {
            sessionFactory = SessionFactoryManager.getInstance();
            factory = sessionFactory.factory();
            session = factory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Dvd> criteriaQuery = builder.createQuery(Dvd.class);
            Root<Dvd> root = criteriaQuery.from(Dvd.class);
            criteriaQuery.select(root);
            criteriaQuery.select(root).where(builder.equal(root.get(
                DvdConstants.LABEL_STATUS),status));
            Query  query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (HibernateException e) {
            throw new DvdException(DvdConstants.MESSAGE_EXCEPTION_RETRIEVE);
        } finally {
            sessionFactory.closeSession(session);
        }
    }


    /**
      * @{inheritdoc}
      */
    public Boolean restoreDvd(Dvd dvd) throws DvdException {
        Session session = null;
        SessionFactory factory;
        Transaction transaction = null;
        try{
            sessionFactory = SessionFactoryManager.getInstance();
            factory = sessionFactory.factory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            dvd.setStatus(DvdConstants.LABEL_YES);
            session.update(dvd);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            dvdLogger.error(DvdConstants.MESSAGE_EXCEPTION_RETRIEVE + 
                dvd.getId(),e);
            if(null != transaction) {
                transaction.rollback();
            }
            throw new DvdException(DvdConstants.MESSAGE_EXCEPTION_RETRIEVE + 
                dvd.getId());
        } finally {
            session.close();
        }
    }
}
