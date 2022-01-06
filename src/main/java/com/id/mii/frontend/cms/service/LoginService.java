/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.service;

import com.id.mii.frontend.cms.model.data.LoginRequestDto;
import com.id.mii.frontend.cms.model.data.LoginResponseDto;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author akhma
 */

@Service
public class LoginService {
    private RestTemplate restTemplate;
    
    @Value("${api.baseUrl}/login")
    private String url;
    
    @Autowired
    public LoginService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public boolean login(LoginRequestDto request){
        try {
            ResponseEntity<LoginResponseDto> res = restTemplate
                    .postForEntity(url, request, LoginResponseDto.class);
            
            if (res.getStatusCode() == HttpStatus.OK) {
                setAuthentication(res.getBody());
                return true;
            }
            
        } catch (RestClientException e) {
            
        }
        return false;
    }
    
    private void setAuthentication(LoginResponseDto res){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(res.getUsername(), null, getAuthorities(res.getAuthorities()));
        
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
    
    private Collection<GrantedAuthority> getAuthorities(List<String> authorities){
        return authorities.stream().map(auth -> new SimpleGrantedAuthority(auth)).collect(Collectors.toList());
    }
}
