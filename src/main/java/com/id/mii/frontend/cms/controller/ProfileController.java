/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.controller;

import com.id.mii.frontend.cms.model.User;
import com.id.mii.frontend.cms.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author akhma
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {
    private ProfileService profileService;
    
    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }
    
    
    @GetMapping("/{tokenCode}")
    public String activate(@PathVariable ("tokenCode") String code) {
        profileService.activate(code);
        return "activation";
    }
    
    @PutMapping("/{tokenCode}")
    public String updatePass(@PathVariable ("tokenCode") String code, @RequestBody User user) {
        profileService.updatePass(code, user);
        return "redirect:/";
    }
    
    @GetMapping
    public String testHTML(){
        return "activation";
    }
    
    
    
}
