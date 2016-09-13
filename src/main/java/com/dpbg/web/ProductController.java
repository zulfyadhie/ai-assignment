package com.dpbg.web;

import com.dpbg.entity.Product;
import com.dpbg.repository.CategoryDao;
import com.dpbg.repository.ProductDao;
import com.dpbg.service.ProductService;
import com.dpbg.web.form.ProductForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by zulfy on 9/12/16.
 */
@Controller
@RequestMapping(value = "/product")
public class ProductController {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductDao productDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private ProductService productService;
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "product/list";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(Model model, @RequestParam(value = "id", required = false) Product product){

        ProductForm productForm = new ProductForm();
        if(product != null){
            BeanUtils.copyProperties(product, productForm);
        }

        model.addAttribute("categoryList", categoryDao.findAll());
        model.addAttribute("productForm", productForm);
        return "product/detail";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public String save(@Valid ProductForm productForm, BindingResult bindingResult,
                       RedirectAttributes redirectAttributes, Locale locale, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("categoryList", categoryDao.findAll());
            return "product/detail";
        }
        try{
            Product product = new Product();
            BeanUtils.copyProperties(productForm, product);
            productService.save(product);

            String successMsg;
            if(productForm.getId() == null){
                successMsg = messageSource.getMessage("global.notif.success.add", new Object[]{product.getName()}, locale);
            }else{
                successMsg = messageSource.getMessage("global.notif.success.edit", new Object[]{product.getName()}, locale);
            }
            redirectAttributes.addFlashAttribute("successMsg", successMsg);
            return "redirect:/product";
        }catch (Exception e){
            LOGGER.error(e.getMessage(), e.getCause());
        }
        return "product/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id") Product product, Model model, Locale locale, RedirectAttributes redirectAttributes) {
        productDao.delete(product.getId());
        redirectAttributes.addFlashAttribute("successMsg", messageSource.getMessage("global.notif.success.delete", new Object[]{product.getName()}, locale));
        return "redirect:/product";
    }

}