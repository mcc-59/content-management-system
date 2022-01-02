/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.controller;

import com.id.mii.backend.cms.model.User;
import com.id.mii.backend.cms.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author akhma
 */

@RestController
@RequestMapping("/profile")
@Slf4j
public class ProfileController {
    
    private UserService userService;
    
    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }
    
    @PutMapping("/{tokenCode}")
//    @PreAuthorize("hasAuthority('READ_DATA')")
    public User updatePass(@PathVariable ("tokenCode") String code,  @RequestBody User user){
        return userService.updatePassword(code, user);
    }
    
    @GetMapping("/{tokenCode}")
//    @PreAuthorize("hasAuthority('READ_DATA')")
    public User activate(@PathVariable ("tokenCode") String code){
        return userService.activate(code);
    }
    
    
}
