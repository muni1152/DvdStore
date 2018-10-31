package com.ideas2it.dvdstore.dao.impl;

import com.ideas2it.dvdstore.common.DvdConstants;
import com.ideas2it.dvdstore.dao.UserDao;
import com.ideas2it.dvdstore.exception.DvdException;
import com.ideas2it.dvdstore.logger.DvdLogger;
import com.ideas2it.dvdstore.model.User;
import com.ideas2it.dvdstore.sessionFactory.SessionFactoryManager;

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

/** 
  * <p>
  * List actions are performed at the Dao
  * It performs specific operation depend on the user request
  * <p/>
  * 
  * author Muniraj M
  */
public class UserDaoImpl implements UserDao {

    private SessionFactoryManager sessionFactory;
    private DvdLogger dvdLogger = new DvdLogger();

    public User signIn(User user) throws DvdException {
        Session session = null;
        SessionFactory factory;
        try {
            sessionFactory = SessionFactoryManager.getInstance();
            factory = sessionFactory.factory();
            session = factory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery =
                builder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);
            Predicate[] predicates = new Predicate[3];
            predicates[0] = builder.equal(root.get("userName"),user.getUserName());
            predicates[1] = builder.equal(root.get("password"),user.getPassword());
            predicates[2] = builder.equal(root.get("role"),user.getRole());
            criteriaQuery.select(root).where(predicates);
            return (User)session.createQuery(criteriaQuery).uniqueResult();
        } catch (HibernateException e) {
            dvdLogger.error(DvdConstants.MESSAGE_EXCEPTION_SEARCH ,e);
            throw new DvdException(DvdConstants.MESSAGE_EXCEPTION_SEARCH);
        } finally {
            session.close();
        }
    }

    /**
      * @{inheritdoc}
      */
    public Boolean saveUser(User user) throws DvdException {
        Session session = null;
        SessionFactory factory;
        Transaction transaction = null;
        try {
            sessionFactory = SessionFactoryManager.getInstance();
            factory = sessionFactory.factory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if(null != transaction) {
                transaction.rollback();
            }
            throw new DvdException(DvdConstants.MESSAGE_EXCEPTION_CREATE);
        } finally {
            session.close();
        }
    }
}
