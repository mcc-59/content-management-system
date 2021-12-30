/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.service;

import com.id.mii.frontend.cms.model.Content;
import com.id.mii.frontend.cms.model.data.CategoryDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ContentService {
    
    private RestTemplate restTemplate;
    
    @Value("${api.baseUrl}/content")
    private String url;

    @Autowired
    public ContentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<CategoryDto> getAll() {
        return restTemplate
                .exchange(url, HttpMethod.GET, null, 
                        new ParameterizedTypeReference<List<CategoryDto>>() {})
                .getBody();
    }

    public Content getById(Long id) {
        return restTemplate
                .getForObject(url + "/" + id, Content.class);
    }

    public void create(Content content) {
        restTemplate
                .postForEntity(url, content, Content.class);
    }

    public void update(Long id, Content content) {
        restTemplate
                .put(url + "/" + id, content, Content.class);
    }
    
    public void delete(Long id) {
        restTemplate
                .delete(url + "/" + id, Content.class);
    }
}
