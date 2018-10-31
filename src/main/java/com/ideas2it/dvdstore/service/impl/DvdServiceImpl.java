package com.ideas2it.dvdstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.dvdstore.common.DvdConstants;
import com.ideas2it.dvdstore.dao.DvdDao;
import com.ideas2it.dvdstore.service.impl.CategoryServiceImpl;
import com.ideas2it.dvdstore.dao.impl.DvdDaoImpl;
import com.ideas2it.dvdstore.exception.CategoryException;
import com.ideas2it.dvdstore.exception.DvdException;
import com.ideas2it.dvdstore.logger.DvdLogger;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.service.CategoryService;
import com.ideas2it.dvdstore.service.DvdService;

/** 
  * <p>
  * DvdSevice class performs  validation process and business logic 
  * Crete ,Update, Delete, Search, Display method's validation process done here
  * <p/>
  * 
  * author Muniraj M
  */
public class DvdServiceImpl implements DvdService {

    private DvdDao dvdDao = new DvdDaoImpl();
    private CategoryService categoryService = new CategoryServiceImpl();

    /**
      * @{inheritdoc}
      */
    public Boolean createDvd(Dvd dvd) throws DvdException {
        return dvdDao.insertDvd(dvd);
    }  
    /**
      * @{inheritdoc}
      */
    public Dvd searchDvd(int dvdId) throws DvdException {
        return dvdDao.searchDvd(dvdId);
    }
    
    /**
      * @{inheritdoc}
      */
    public Boolean updateDvd(Dvd dvd) throws DvdException {
          return dvdDao.updateDvd(dvd);
    }
    
    /**
      * @{inheritdoc}
      */
    public Boolean deleteDvd(Dvd dvd) throws DvdException {
        return dvdDao.deleteDvd(dvd); 
    }

    /**
      * @{inheritdoc}
      */
    public List<Dvd> getDvds(String status) throws DvdException {
        return dvdDao.getDvds(status);
    }
   
    /**
      * @{inheritdoc}
      */
    public Boolean restoreDvd(Dvd dvd) throws DvdException {
        return dvdDao.restoreDvd(dvd);
    }

    /**
      * @{inheritdoc}
      */
    public List<Category> getCategories(String status) throws 
            CategoryException {
        return categoryService.getCategories(status);
    }

    
    
}
    
