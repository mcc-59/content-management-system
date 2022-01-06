/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.service;

import com.id.mii.frontend.cms.model.Type;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author sihlihis
 */
@Service
public class TypeService {

    private RestTemplate restTemplate;
    
    @Value("${api.baseUrl}/type")
    private String url;

    public TypeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Type> getAll() {
        return restTemplate
                .exchange(url, HttpMethod.GET, null, 
                        new ParameterizedTypeReference<List<Type>>() {})
                .getBody();
    }

    public Type getById(Long id) {
        return restTemplate
                .getForObject(url + "/" + id, Type.class);
    }

    public void create(Type type) {
        restTemplate
                .postForEntity(url, type, Type.class);
    }

    public void update(Long id, Type type) {
        restTemplate
                .put(url + "/" + id, type, Type.class);
    }
}
