/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.controller;

import com.id.mii.frontend.cms.model.Category;
import com.id.mii.frontend.cms.model.data.EmailSenderDto;
import com.id.mii.frontend.cms.service.CategoryService;
import com.id.mii.frontend.cms.service.EmailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author akhma
 */
@Controller
@RequestMapping("/email")
public class EmailController {
    
    private EmailService emailService;
    private CategoryService categoryService;
    
    @Autowired
    public EmailController(EmailService emailService, CategoryService categoryService) {
        this.emailService = emailService;
        this.categoryService = categoryService;
    }
    
    
    @PostMapping("/forget")
    public String create(EmailSenderDto emailSenderDto) {
        emailService.emailForget(emailSenderDto);
        return "redirect:/login";
    }
    
    @GetMapping("/test")
    @ResponseBody
    public List<Category> getAll(){
        return categoryService.getAll();
    }
    
}
