package com.dpbg.service;

import com.dpbg.entity.Product;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;

/**
 * Created by zulfy on 9/12/16.
 */
public interface ProductService {

    DataSet<Product> findWithDatatablesCriterias(DatatablesCriterias criterias);
    Product save(Product entity);

}