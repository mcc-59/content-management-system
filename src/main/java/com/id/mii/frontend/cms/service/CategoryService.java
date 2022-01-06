/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.service;

import com.id.mii.frontend.cms.model.Category;
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
public class CategoryService {

    private RestTemplate restTemplate;
    
    @Value("${api.baseUrl}/category")
    private String url;

    public CategoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Category> getAll() {
        return restTemplate
                .exchange(url, HttpMethod.GET, null, 
                        new ParameterizedTypeReference<List<Category>>() {})
                .getBody();
    }

    public Category getById(Long id) {
        return restTemplate
                .getForObject(url + "/" + id, Category.class);
    }

    public void create(Category category) {
        restTemplate
                .postForEntity(url, category, Category.class);
    }

    public void update(Long id, Category category) {
        restTemplate
                .put(url + "/" + id, category, Category.class);
    }
    
    public void delete(Long id) {
        restTemplate
                .delete(url + "/" + id, Category.class);
    }
}
