package com.dpbg.repository;

import com.dpbg.entity.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zulfy on 9/12/16.
 */
public interface CategoryDao extends CrudRepository<Category, Long> {
}
