package com.ideas2it.dvdstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.dvdstore.common.DvdConstants;
import com.ideas2it.dvdstore.dao.CategoryDao;
import com.ideas2it.dvdstore.dao.impl.CategoryDaoImpl;
import com.ideas2it.dvdstore.exception.CategoryException;
import com.ideas2it.dvdstore.exception.DvdException;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();

    /** @{inheritdoc}
      */
    public Boolean createCategory(Category category) throws CategoryException {
        return categoryDao.insertCategory(category);
    }

    /**
      * @{inheritdoc}
      */
    public Category displayDvdsByCategories(int categoryId) throws 
            CategoryException {
        return categoryDao.displayDvdsByCategories(categoryId);
    }
    
    /**
      * @{inheritdoc}
      */
    public Boolean updateCategory(Category category) throws CategoryException {
        return categoryDao.updateCategory(category);
    }
    
    /**
      * @{inheritdoc}
      */
    public Boolean deleteCategory(Category category) throws CategoryException {
        return categoryDao.deleteCategory(category); 
    }

     /**
      * @{inheritdoc}
      */
    public List<Category> getCategories(String status) throws CategoryException {
        return categoryDao.getCategories(status);
    }
    
    /**
      * @{inheritdoc}
      */
    public Category getCategory(int categoryId) throws 
            CategoryException  {
        return categoryDao.getCategory(categoryId);
    }

    /**
      * @{inheritdoc}
      */
    public Boolean restoreCategory(Category category) throws CategoryException {
      return categoryDao.restoreCategory(category);
    }

}
      
