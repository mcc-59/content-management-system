/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.service;

import com.id.mii.frontend.cms.model.Category;
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
public class CategoryService {

    private RestTemplate restTemplate;

    @Value("${api.baseUrl}/category")
    private String url;

    @Autowired
    public CategoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Category getById(Long id) {
        return restTemplate.exchange(url + "/" + id, HttpMethod.GET, null, new ParameterizedTypeReference<Category>() {
        }).getBody();
    }

}
