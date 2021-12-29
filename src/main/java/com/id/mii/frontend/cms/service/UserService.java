/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.service;

import com.id.mii.frontend.cms.model.User;
import java.util.List;
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
public class UserService {
    
    private RestTemplate restTemplate;
    
    @Value("${api.baseUrl}/user")
    private String url;
    
    @Autowired
    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public List<User> getAll(){
        return restTemplate
                .exchange(url, HttpMethod.GET, null, 
                        new ParameterizedTypeReference<List<User>>(){}).getBody();
    }
    
    public List<User> findWriter(){
        return restTemplate
                .exchange(url + "/writer", HttpMethod.GET, null, 
                        new ParameterizedTypeReference<List<User>>(){}).getBody();
    }
    
    public User getById(Long id){
      return restTemplate
                .exchange(url + "/" + id, HttpMethod.GET, null, 
                        new ParameterizedTypeReference<User>() {}).getBody();
    }
    
    public void create (User user){
        restTemplate.postForEntity(url, user, User.class);
    }
    
    public void update (Long id, User user){
        restTemplate.put(url + "/" + id, user, User.class);
    }
    
    public void delete(Long id) {
        restTemplate
                .delete(url + "/" + id, User.class);
    }
    
}
