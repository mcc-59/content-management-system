/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.service;

import com.id.mii.backend.cms.model.AppUserDetail;
import com.id.mii.backend.cms.model.User;
import com.id.mii.backend.cms.model.data.LoginRequestDto;
import com.id.mii.backend.cms.model.data.LoginResponseDto;
import com.id.mii.backend.cms.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author akhma
 */

@Service
public class LoginService {
    
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public LoginResponseDto loginUser(LoginRequestDto login){
        User user = userRepository.findByUsername(login.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username not found"));
        
        if (!passwordEncoder.matches(login.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Wrong password");
        }
        
        LoginResponseDto response = new LoginResponseDto();
        response.setUsername(user.getUsername());
        response.setAuthorities(authorities(new AppUserDetail(user)));
        
        return response;
    }
    
    private List<String> authorities(AppUserDetail appUserDetail){
        return appUserDetail.getAuthorities().stream()
                .map(auth -> auth.getAuthority()).collect(Collectors.toList());
           
    }
    
    
}
