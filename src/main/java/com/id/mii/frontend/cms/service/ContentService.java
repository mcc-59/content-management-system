/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.service;

import com.id.mii.frontend.cms.model.Content;
import com.id.mii.frontend.cms.model.ContentResponse;
import com.id.mii.frontend.cms.model.data.ContentDto;
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

    public List<ContentResponse> getAll() {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ContentResponse>>() {
        }).getBody();
    }

    public Content getById(Long id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Content>() {
        }).getBody();
    }
    
    public List<Content> getContentByWriter(Long id) {
        return restTemplate.exchange(
                url + "/content-writer/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Content>>() {
        }).getBody();
    }
    
    public List<Content> getContentByUsername(String username) {
        return restTemplate.exchange(
                url + "/contentwriter/" + username,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Content>>() {
        }).getBody();
    }

    public Content create(ContentDto contentDto) {
        HttpEntity<ContentDto> httpEntity = new HttpEntity<>(contentDto);

        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<Content>() {
        }).getBody();
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
