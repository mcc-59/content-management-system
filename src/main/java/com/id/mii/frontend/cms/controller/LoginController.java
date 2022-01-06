/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.controller;

import com.id.mii.frontend.cms.model.User;
import com.id.mii.frontend.cms.model.data.LoginRequestDto;
import com.id.mii.frontend.cms.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author akhma
 */

@Controller
@RequestMapping("/login")
public class LoginController {
    
    private LoginService loginService;
    
    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }
    
    @GetMapping
    public String login(User user, LoginRequestDto loginRequestDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        
        return "redirect:/";
    }
    
    @PostMapping("/login")
    public String login(LoginRequestDto loginRequestDto, BindingResult result) {

        if (result.hasErrors()) {
            return "login";
        }
        
        if (!loginService.login(loginRequestDto)) {
            return "redirect:/login?error=true";
        }

        return "redirect:/";
    }
    
    @PostMapping("/logout")
    public String logout(){
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/login?logout=true";
    }
    
    
}
