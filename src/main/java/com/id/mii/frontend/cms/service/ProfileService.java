/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.service;

import com.id.mii.frontend.cms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author akhma
 */
@Service
public class ProfileService {
    
    private RestTemplate restTemplate;
    
    @Autowired
    public ProfileService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    @Value("${api.baseUrl}/profile")
    private String url;
    
    public void activate(String tokenCode){
        restTemplate.exchange(url + "/" + tokenCode, HttpMethod.GET, null, 
                        new ParameterizedTypeReference<User>(){});
    }
    
    public void updatePass(String tokenCode, User user){
        restTemplate.exchange(url + "/" + tokenCode, HttpMethod.PUT, new HttpEntity(user), 
                        new ParameterizedTypeReference<User>(){});
    }
}
