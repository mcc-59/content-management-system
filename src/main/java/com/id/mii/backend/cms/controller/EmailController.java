/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.controller;

import com.id.mii.backend.cms.model.data.EmailSenderDto;
import com.id.mii.backend.cms.model.data.RequestQcDto;
import com.id.mii.backend.cms.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author akhma
 */

@RestController
@RequestMapping("/email")
public class EmailController {
    
    private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }
    
    @PostMapping("/forget")
    public ResponseEntity<EmailSenderDto> sendEmailForget(@RequestBody EmailSenderDto email) {
        return new ResponseEntity(emailService.sendEmailForget(email), HttpStatus.OK);
    }
    
    @PostMapping("/request")
    public ResponseEntity<RequestQcDto> sendEmailRequest(@RequestBody RequestQcDto request) {
        return new ResponseEntity(emailService.requestQc(request), HttpStatus.OK);
    }
    
    
    
}
