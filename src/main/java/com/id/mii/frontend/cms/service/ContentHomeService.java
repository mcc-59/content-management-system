/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.service;

import com.id.mii.frontend.cms.model.data.ContentDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author akhma
 */
@Service
public class ContentHomeService {
    private RestTemplate restTemplate;
    
    @Value("${api.baseUrl}/content-home")
    private String url;
    
    @Autowired
    public ContentHomeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public List<ContentDto> getByCategoryId(Long id) {
        return restTemplate
                .exchange(url + "/category/" + id, HttpMethod.GET, null, 
                        new ParameterizedTypeReference<List<ContentDto>>() {})
                .getBody();
    }
    
    public List<ContentDto> getFirstTen() {
        return restTemplate
                .exchange(url, HttpMethod.GET, null, 
                        new ParameterizedTypeReference<List<ContentDto>>() {})
                .getBody();
    }
    
    public List<ContentDto> getTopView() {
        return restTemplate
                .exchange(url + "/view", HttpMethod.GET, null, 
                        new ParameterizedTypeReference<List<ContentDto>>() {})
                .getBody();
    }
    
    public ContentDto getTrending() {
        return restTemplate
                .exchange(url + "/trending", HttpMethod.GET, null, 
                        new ParameterizedTypeReference<ContentDto>() {})
                .getBody();
    }
}
