package com.dpbg.web.ajax;

import com.dpbg.entity.Product;
import com.dpbg.service.ProductService;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zulfy on 9/12/16.
 */
@RestController
@RequestMapping(value = "/ajax/product")
public class ProductAjaxController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/list")
    public DatatablesResponse<Product> list(@DatatablesParams DatatablesCriterias criterias){
        DataSet<Product> taskDataSet = productService.findWithDatatablesCriterias(criterias);
        return DatatablesResponse.build(taskDataSet, criterias);
    }

}