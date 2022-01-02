/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.service;

import com.id.mii.frontend.cms.model.data.EmailSenderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author akhma
 */
@Service
public class EmailService {
    private RestTemplate restTemplate;
    
    @Value("${api.baseUrl}/email")
    private String url;

    @Autowired
    public EmailService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public void emailForget(EmailSenderDto emailSenderDto) {
        restTemplate
                .postForEntity(url + "/forget", emailSenderDto, EmailSenderDto.class);
    }
}
