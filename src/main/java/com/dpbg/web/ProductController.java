package com.dpbg.web;

import com.dpbg.entity.Product;
import com.dpbg.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by zulfy on 9/12/16.
 */
@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "product/list";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(Model model, @RequestParam(value = "id", required = false) Product product, HttpServletRequest request, HttpServletResponse response){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

//        TaskForm taskForm = new TaskForm();
//        if(task != null){
//            taskForm.setId(task.getId());
//            taskForm.setName(task.getName());
//            taskForm.setStartDate(task.getStartDate());
//            taskForm.setEndDate(task.getEndDate());
//            taskForm.setDescription(task.getDescription());
//            taskForm.setOrganization(task.getOrganization());
//            taskForm.setDepartment(task.getDepartment());
//            taskForm.setSpecialization(task.getSpecialization());
//            taskForm.setAssignee(task.getAssignee());
//            taskForm.setStatus(task.getStatus());
//            taskForm.setRealLoad(task.getRealLoad());
//            taskForm.setNote(task.getNote());
//
//            Boolean isPic = task.getAssignee().getUsername().equals(auth.getName());
//
//            if(request.isUserInRole("ROLE_ADMIN") && task.getStatus() == Task.TASK_STATUS_DRAFT){
//                taskForm.setReadOnly(false);
//            } else if(request.isUserInRole("ROLE_ADMIN")){
//                taskForm.setReadOnly(true);
//            } else if(request.isUserInRole("ROLE_STAFF") && task.getStatus() == Task.TASK_STATUS_DRAFT){
//                return "redirect:/error/403";
//            } else if(isPic && task.getStatus() == Task.TASK_STATUS_IN_PROGRESS){
//                taskForm.setReadOnly(false);
//            } else if(task.getStatus() == Task.TASK_STATUS_DONE){
//                taskForm.setReadOnly(true);
//            }
//
//            model.addAttribute("departmentList", departmentDao.findByOrganizationIdOrderByNameAsc(task.getOrganization().getId()));
//            model.addAttribute("specializationList", specializationDao.findByDepartmentIdOrderByIdAsc(task.getDepartment().getId()));
//            model.addAttribute("staffList", staffDao.findBySpecializationIdOrderByFirstNameAsc(task.getSpecialization().getId()));
//        }
//        else{
//            taskForm.setReadOnly(false);
//        }
//
//        model.addAttribute("organizationList", organizationDao.findAllByOrderByNameAsc());
//        model.addAttribute("taskForm", taskForm);
        return "product/detail";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id") Product product, Model model, Locale locale) {
        productDao.delete(product.getId());
        model.addAttribute("successMsg", messageSource.getMessage("global.notif.success.delete", new Object[]{product.getName()}, locale));
        return "product/list";
    }

}