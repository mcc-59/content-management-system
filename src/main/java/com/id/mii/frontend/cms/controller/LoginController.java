/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    
    @GetMapping
    public String login(Model model) {
        return "login";
    }   
    
    @PostMapping("/logout")
    public String logout(){
//        SecurityContextHolder.getContext().setAuthentication(null);
        System.out.println("cie logout");
        return "redirect:/login?logout=true";
    }
    
    
}
