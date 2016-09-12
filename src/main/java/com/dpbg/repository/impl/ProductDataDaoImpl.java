package com.dpbg.repository.impl;

import com.dpbg.entity.Product;
import com.dpbg.repository.DataDaoUtils;
import com.dpbg.repository.ProductDataDao;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by zulfy on 9/12/16.
 */
@Repository
public class ProductDataDaoImpl implements ProductDataDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findWithDatatablesCriterias(DatatablesCriterias criterias) {
        StringBuilder queryBuilder = new StringBuilder("SELECT t FROM Product t");

        /**
         * Step 1: global and individual column filtering
         */
        queryBuilder.append(DataDaoUtils.getFilterQuery(criterias));

        /**
         * Step 2: sorting
         */
        DataDaoUtils.sortingData(criterias, queryBuilder);
        TypedQuery<Product> query = entityManager.createQuery(
                queryBuilder.toString(), Product.class);


        /**
         * Step 3: paging
         */
        query.setFirstResult(criterias.getStart());
        query.setMaxResults(criterias.getLength());
        return query.getResultList();
    }

    @Override
    public Long getTotalCount() {
        Query query = entityManager.createQuery("SELECT COUNT(t) FROM Product t");
        return (Long) query.getSingleResult();
    }

    @Override
    public Long getFilteredCount(DatatablesCriterias criterias) {
        StringBuilder queryBuilder = new StringBuilder("SELECT COUNT(t) FROM Product t");
        queryBuilder.append(DataDaoUtils.getFilterQuery(criterias));
        Query query = entityManager.createQuery(queryBuilder.toString());
        return (Long) query.getSingleResult();
    }

}