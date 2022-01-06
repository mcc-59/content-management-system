/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author akhma
 */

@Service
public class DashboardDetailService {
    
    private RestTemplate restTemplate;
    
    @Value("${api.baseUrl}/detail")
    private String url;
    
    @Autowired
    public DashboardDetailService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public Integer totalWriter() {
        return restTemplate
                .getForObject(url + "/writer", Integer.class);
    }
    public Integer totalContent() {
        return restTemplate
                .getForObject(url + "/content", Integer.class);
    }
    public Integer totalView() {
        return restTemplate
                .getForObject(url + "/view", Integer.class);
    }
    public Integer totalPending() {
        return restTemplate
                .getForObject(url + "/pending", Integer.class);
    }
    public Integer totalReject() {
        return restTemplate
                .getForObject(url + "/reject", Integer.class);
    }
}
