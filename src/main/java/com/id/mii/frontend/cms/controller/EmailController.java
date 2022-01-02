/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.controller;

import com.id.mii.frontend.cms.model.data.EmailSenderDto;
import com.id.mii.frontend.cms.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author akhma
 */
@Controller
@RequestMapping("/email")
public class EmailController {
    
    private EmailService emailService;
    
    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }
    
    @PostMapping("/forget")
    public String create(EmailSenderDto emailSenderDto) {
        emailService.emailForget(emailSenderDto);
        return "redirect:/login";
    }
    
}
