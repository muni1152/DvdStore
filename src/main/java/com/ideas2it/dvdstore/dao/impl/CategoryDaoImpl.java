package com.ideas2it.dvdstore.dao.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Criteria;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.apache.log4j.Logger;


import com.ideas2it.dvdstore.common.DvdConstants;
import com.ideas2it.dvdstore.dao.CategoryDao;
import com.ideas2it.dvdstore.exception.CategoryException;
import com.ideas2it.dvdstore.logger.DvdLogger;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.sessionFactory.SessionFactoryManager;

/** 
  * <p>
  * DataBase related actions are performed at the Dao
  * It performs specific operation depend on the user request
  * <p/>
  * 
  * author Muniraj M
  */
public class CategoryDaoImpl implements CategoryDao{

     private DvdLogger dvdLogger = new DvdLogger();
     private SessionFactoryManager sessionFactory;

    /**
      * @{inheritdoc}
      */
    public Boolean insertCategory(Category category) throws CategoryException {
        Session session = null;
        SessionFactory factory;
        Transaction transaction = null;
        try {
            sessionFactory = SessionFactoryManager.getInstance();
            factory = sessionFactory.factory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            category.setStatus(DvdConstants.LABEL_YES);
            session.save(category);
            transaction.commit();
            return Boolean.TRUE;
         } catch (ConstraintViolationException e) {
            throw new CategoryException(DvdConstants.MESSAGE_DUPLICATE + 
                "For Category Name = " + category.getCategory());
         
        } catch (HibernateException e) {
            dvdLogger.error(DvdConstants.MESSAGE_EXCEPTION_CATEGORY_CREATE + 
                    category.getCategory(), e);
            if(null != transaction) {
                transaction.rollback();
            }
            throw new CategoryException(DvdConstants.
                MESSAGE_EXCEPTION_CATEGORY_CREATE +  category.getCategory());
        } finally {
            session.close();
        }
    }

    /**
      * @{inheritdoc}
      */
    public Boolean updateCategory(Category category) throws CategoryException {
        Session session = null;
        SessionFactory factory;
        Transaction transaction = null;
        try{
            sessionFactory = SessionFactoryManager.getInstance();
            factory = sessionFactory.factory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.update(category);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            dvdLogger.error(DvdConstants.MESSAGE_EXCEPTION_CATEGORY_UPDATE + 
                category.getId(),e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new CategoryException(DvdConstants.
                MESSAGE_EXCEPTION_CATEGORY_UPDATE + category.getId());
        } catch (PersistenceException e) {
            throw new CategoryException(DvdConstants.MESSAGE_DUPLICATE + 
                "For Category Name = " + category.getCategory());
        } finally {
            session.close();
        }
    }

    /**
      * @{inheritdoc}
      */
    public Category displayDvdsByCategories(int categoryId) throws 
            CategoryException {
        Session session = null;
        SessionFactory factory;
        Transaction transaction = null;
        try {
            sessionFactory = SessionFactoryManager.getInstance();
            factory = sessionFactory.factory();
            session = factory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Category> criteriaQuery = 
                builder.createQuery(Category.class);
            Root<Category> root = criteriaQuery.from(Category.class);
            criteriaQuery.select(root);
            criteriaQuery.select(root).where(builder.equal(root.get(
                DvdConstants.ID),categoryId));
            return (Category)session.createQuery(criteriaQuery).uniqueResult();
        } catch (HibernateException e) {
            dvdLogger.error(DvdConstants.
                MESSAGE_EXCEPTION_VIEW_DVDS_TO_CATEGORY + categoryId, e);
            throw new CategoryException(DvdConstants.
                MESSAGE_EXCEPTION_VIEW_DVDS_TO_CATEGORY + categoryId);
        } finally {
            session.close();
        }
    }

    /**
      * @{inheritdoc}
      */
    public Boolean deleteCategory(Category category) throws CategoryException {
        Session session = null;
        SessionFactory factory;
        Transaction transaction = null;
        try{
            sessionFactory = SessionFactoryManager.getInstance();
            factory = sessionFactory.factory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            category.setStatus(DvdConstants.LABEL_NO);
            session.update(category);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            dvdLogger.error(DvdConstants.MESSAGE_EXCEPTION_CATEGORY_DELETE + 
                category.getId(),e);
            if(null != transaction) {
                transaction.rollback();
            }
            throw new CategoryException(DvdConstants.
               MESSAGE_EXCEPTION_CATEGORY_DELETE +  category.getId());
        } finally {
            session.close();
        }
    }
  
    /**
      * @{inheritdoc}
      */
    public List<Category> getCategories(String status) throws 
            CategoryException {
        Session session = null;
        SessionFactory factory;
        Transaction transaction = null;
        try {
            sessionFactory = SessionFactoryManager.getInstance();
            factory = sessionFactory.factory();
            session = factory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Category> criteriaQuery = 
                builder.createQuery(Category.class);
            Root<Category> root = criteriaQuery.from(Category.class);
            criteriaQuery.select(root);
            criteriaQuery.select(root).where(builder.equal(root.
                get(DvdConstants.LABEL_STATUS),  status));
            Query  query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (HibernateException e) {
            throw new CategoryException(DvdConstants.
                MESSAGE_EXCEPTION_CATEGORY_RETRIEVE);
        } finally {
            session.close();
        }
    }
   
    /**
      * @{inheritdoc}
      */
    public Boolean restoreCategory(Category category) throws CategoryException {
        Session session = null;
        SessionFactory factory;
        Transaction transaction = null;
        try{
            System.out.println(category.getCategory());
            sessionFactory = SessionFactoryManager.getInstance();
            factory = sessionFactory.factory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            category.setStatus(DvdConstants.LABEL_YES);
            session.update(category);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            dvdLogger.error(DvdConstants.MESSAGE_EXCEPTION_CATEGORY_RETRIEVE + 
                 + category.getId(), e);
            if(null != transaction) {
                transaction.rollback();
            }
            throw new CategoryException(DvdConstants. 
                MESSAGE_EXCEPTION_CATEGORY_RETRIEVE + category.getId());
        } finally {
            session.close();
        }
    }

    /**
      * @{inheritdoc}
      */
    public Category getCategory(int categoryId) throws CategoryException {
        Session session = null;
        SessionFactory factory;
        Transaction transaction = null;
        try {
            sessionFactory = SessionFactoryManager.getInstance();
            factory = sessionFactory.factory();
            session = factory.openSession();
            return (Category) session.get(Category.class,categoryId);
        } catch (HibernateException e) {
            dvdLogger.error(DvdConstants.MESSAGE_EXCEPTION,e);
            throw new CategoryException(DvdConstants.MESSAGE_EXCEPTION_CATEGORY_SEARCH + 
                categoryId);
        } finally {
            session.close();
        }
    }
    
}
                          
        

   
