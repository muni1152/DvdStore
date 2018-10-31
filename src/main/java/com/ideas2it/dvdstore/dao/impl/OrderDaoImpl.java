package com.ideas2it.dvdstore.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ideas2it.dvdstore.common.DvdConstants;
import com.ideas2it.dvdstore.dao.OrderDao;
import com.ideas2it.dvdstore.exception.DvdException;
import com.ideas2it.dvdstore.logger.DvdLogger;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.model.Orders;
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
public class OrderDaoImpl implements OrderDao {

    private SessionFactoryManager sessionFactory;
    private DvdLogger dvdLogger = new DvdLogger();

    /**
      * @{inheritdoc}
      */
    public Boolean placeOrder(Orders order) throws DvdException {
        Session session = null;
        SessionFactory factory;
        Transaction transaction = null;
        try{
            sessionFactory = SessionFactoryManager.getInstance();
            factory = sessionFactory.factory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            dvdLogger.error(DvdConstants.MESSAGE_EXCEPTION_CREATE + 
                order.getCustomerId(),e);
            if(null != transaction) {
                transaction.rollback();
            }
            throw new DvdException(DvdConstants.MESSAGE_EXCEPTION_CREATE + 
                order.getCustomerId());
        } finally {
            session.close();
        }
    }
 
    /**
      * @{inheritdoc}
      */
    public Boolean cancelOrder(Orders order) throws DvdException {
    
        Session session = null;
        SessionFactory factory;
        Transaction transaction = null;
        try{
            sessionFactory = SessionFactoryManager.getInstance();
            factory = sessionFactory.factory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.delete(order);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            dvdLogger.error(DvdConstants.MESSAGE_EXCEPTION_DELETE +
                order.getOrderId(),e);
            if(null != transaction) {
                transaction.rollback();
            }
            throw new DvdException(DvdConstants.MESSAGE_EXCEPTION_DELETE + 
                order.getOrderId());
        } finally {
            session.close();
        }
    }

    /**
      * @{inheritdoc}
      */
    public Orders searchOrder(int order_Id) throws DvdException {
        Session session = null;
        SessionFactory factory;
        try {
            sessionFactory = SessionFactoryManager.getInstance();
            factory = sessionFactory.factory();
            session = factory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Orders> criteriaQuery =
                builder.createQuery(Orders.class);
            Root<Orders> root = criteriaQuery.from(Orders.class);
            criteriaQuery.select(root);
            Predicate[] predicates = new Predicate[1];
            predicates[0] = builder.equal(root.get("orderId"),order_Id);
            criteriaQuery.select(root).where(predicates);
            return (Orders)session.createQuery(criteriaQuery).uniqueResult();
        } catch (HibernateException e) {
            dvdLogger.error(DvdConstants.MESSAGE_EXCEPTION_SEARCH + order_Id,e);
            throw new DvdException(DvdConstants.MESSAGE_EXCEPTION_SEARCH + order_Id);
        } finally {
            session.close();
        }
    }

    /**
       * @{inheritdoc}
       */
    public List<Orders> getOrders() throws DvdException {
   
        Session session = null;
        SessionFactory factory;
        Transaction transaction = null;
        try {
            sessionFactory = SessionFactoryManager.getInstance();
            factory = sessionFactory.factory();
            session = factory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Orders> criteriaQuery = 
                builder.createQuery(Orders.class);
            Root<Orders> root = criteriaQuery.from(Orders.class);
            criteriaQuery.select(root);
            Query  query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (HibernateException e) {
            dvdLogger.error(DvdConstants.MESSAGE_EXCEPTION_RETRIEVE,e);
            throw new DvdException(DvdConstants.MESSAGE_EXCEPTION_RETRIEVE);
        } finally {
            session.close();
        }
    }

}

