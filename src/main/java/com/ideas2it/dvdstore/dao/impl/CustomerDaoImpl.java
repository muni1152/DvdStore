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

import org.hibernate.Criteria;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.apache.log4j.Logger;

import com.ideas2it.dvdstore.common.DvdConstants;
import com.ideas2it.dvdstore.dao.CustomerDao;
import com.ideas2it.dvdstore.exception.DvdException;
import com.ideas2it.dvdstore.logger.DvdLogger;
import com.ideas2it.dvdstore.model.Address;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.model.User;
import com.ideas2it.dvdstore.sessionFactory.SessionFactoryManager;


/** 
  * <p>
  * List actions are performed at the Dao
  * It performs specific operation depend on the user request
  * <p/>
  * 
  * author Muniraj M
  */
public class CustomerDaoImpl implements CustomerDao {

    private SessionFactoryManager sessionFactory;
    private DvdLogger dvdLogger = new DvdLogger();

    /**
      * @{inheritdoc}
      */
    public Boolean insertCustomer(Customer customer) throws DvdException {
        Session session = null;
        SessionFactory factory;
        Transaction transaction = null;
        try{
            sessionFactory = SessionFactoryManager.getInstance();
            factory = sessionFactory.factory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            dvdLogger.error(DvdConstants.MESSAGE_EXCEPTION_CUSTOMER_CREATE + 
                customer.getName(),e);
            if(null != transaction) {
                transaction.rollback();
            }
            throw new DvdException(DvdConstants.MESSAGE_EXCEPTION_CUSTOMER_CREATE + 
                customer.getName());
        } finally {
            session.close();
        }
    }

     /**
       * @{inheritdoc}
       */
    public Customer searchCustomerByMobileNumber(String number) throws 
            DvdException {
        Session session = null;
        SessionFactory factory;
        Transaction transaction = null;
        try {
            sessionFactory = SessionFactoryManager.getInstance();
            factory = sessionFactory.factory();
            session = factory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = 
                builder.createQuery(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery.select(root);

            Predicate[] predicates = new Predicate[1];
            predicates[0] = builder.equal(root.get(DvdConstants.MOBILE_NUMBER),
                number);
            criteriaQuery.select(root).where(predicates);
            return (Customer)session.createQuery(criteriaQuery).uniqueResult();
        } catch (HibernateException e) {
            dvdLogger.error(DvdConstants.
                MESSAGE_EXCEPTION_CUSTOMER_SEARCH_MOBILE_NO + number, e);
            throw new DvdException(DvdConstants.
                MESSAGE_EXCEPTION_CUSTOMER_SEARCH_MOBILE_NO + number);
        } finally {
            session.close();
        }
    }

     /**
       * @{inheritdoc}
       */
    public Boolean updateCustomer(Customer customer) throws DvdException {
        Session session = null;
        SessionFactory factory;
        Transaction transaction = null;
        try{
            sessionFactory = SessionFactoryManager.getInstance();
            factory = sessionFactory.factory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.update(customer);
            System.out.println("dao" +customer.getId());
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            dvdLogger.error(DvdConstants.MESSAGE_EXCEPTION_CUSTOMER_UPDATE +
                customer.getId(),e);
            if(null != transaction) {
                transaction.rollback();
            }
            throw new DvdException(DvdConstants.MESSAGE_EXCEPTION_CUSTOMER_UPDATE + 
                customer.getId());
        } finally {
            session.close();
        }
    }

    /**
      * @{inheritdoc}
      */
    public Customer searchCustomer(int customerId) throws DvdException {
        Session session = null;
        SessionFactory factory;
        Transaction transaction = null;
        try {
            sessionFactory = SessionFactoryManager.getInstance();
            factory = sessionFactory.factory();
            session = factory.openSession();
            return (Customer) session.get(Customer.class, customerId);
        } catch (HibernateException e) {
            dvdLogger.error(DvdConstants.MESSAGE_EXCEPTION_CUSTOMER_SEARCH +
                 customerId, e);
            throw new DvdException(DvdConstants.
                 MESSAGE_EXCEPTION_CUSTOMER_SEARCH + customerId);
               
        } finally {
            session.close();
        }
    }

     /**
       * @{inheritdoc}
       */
    public List<Customer> getCustomers() throws DvdException {
   
        Session session = null;
        SessionFactory factory;
        Transaction transaction = null;
        try {
            sessionFactory = SessionFactoryManager.getInstance();
            factory = sessionFactory.factory();
            session = factory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = 
                builder.createQuery(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery.select(root);
            Query  query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (HibernateException e) {
            dvdLogger.error(DvdConstants.MESSAGE_EXCEPTION_CUSTOMER_RETRIEVE,e);
            throw new DvdException(DvdConstants.MESSAGE_EXCEPTION_CUSTOMER_RETRIEVE);
        } finally {
            session.close();
        }
    }
}

