package com.dpbg.repository;

import com.dpbg.entity.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zulfy on 9/12/16.
 */
public interface ProductDao extends CrudRepository<Product, Long> {
}
