package com.dpbg.service.impl;

import com.dpbg.entity.Product;
import com.dpbg.repository.ProductDao;
import com.dpbg.repository.ProductDataDao;
import com.dpbg.service.ProductService;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zulfy on 9/12/16.
 */
@Service(value = "productService")
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductDataDao productDataDao;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class, timeout = 30)
    public DataSet<Product> findWithDatatablesCriterias(DatatablesCriterias criterias) {
        List<Product> dataList =  productDataDao.findWithDatatablesCriterias(criterias);
        Long count  = productDataDao.getTotalCount();
        Long countFiltered = productDataDao.getFilteredCount(criterias);
        return new DataSet<>(dataList, count, countFiltered);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class, timeout = 30)
    public Product save(Product entity) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Product product;
        if(entity.getId() != null){
            product = productDao.findOne(entity.getId());
            product.setId(entity.getId());
            product.setName(entity.getName());
            product.setDescription(entity.getDescription());
            product.setPrice(entity.getPrice());
            product.setCategory(entity.getCategory());
            product.setAvailable(entity.getAvailable());
            product.setUpdatedBy(auth.getName());
        }else{
            product = entity;
            product.setCreatedBy(auth.getName());
        }
        return productDao.save(product);
    }
}