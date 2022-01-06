/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.config;

import java.io.IOException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author akhma
 */
public class RequestInterceptor implements ClientHttpRequestInterceptor{

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (!request.getURI().getPath().equals("/api/v1/content-home/trending") && !request.getURI().getPath().equals("/api/v1/content-home")
                && !request.getURI().getPath().startsWith("/api/v1/content/") && !request.getURI().getPath().startsWith("/api/v1/content-home/category/")
                && !request.getURI().getPath().startsWith("/api/v1/category/") && !request.getURI().getPath().startsWith("/api/v1/user")
                && !request.getURI().getPath().startsWith("/api/v1/email") && !request.getURI().getPath().startsWith("/api/v1/profile/")
                && !request.getURI().getPath().startsWith("/api/v1/login")) {
            request.getHeaders().add("Authorization", "Basic " + auth.getCredentials());
        }
        
        ClientHttpResponse response = execution.execute(request, body);
        
        return response;
    }
    
}
