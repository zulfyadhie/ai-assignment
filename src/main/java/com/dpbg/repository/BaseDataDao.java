package com.dpbg.repository;

import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;

import java.util.List;

/**
 * Created by zulfy on 9/12/16.
 */
public interface BaseDataDao<T> {

    List<T> findWithDatatablesCriterias(DatatablesCriterias criterias);

    Long getTotalCount();

    Long getFilteredCount(DatatablesCriterias criterias);

}
