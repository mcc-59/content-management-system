/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.controller;

import com.id.mii.frontend.cms.model.User;
import com.id.mii.frontend.cms.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author akhma
 */

@Controller
@RequestMapping("/user")
public class UserController {
    
    private UserService userService;
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.getAll());
        return "userTemp/index";
    }
    
    @GetMapping("/writer")
    public String listWriter(Model model) {
        model.addAttribute("users", userService.findWriter());
        return "userTemp/listWriter";
    }
    
    @GetMapping("/form")
    public String form(User user) {
        return "userTemp/addForm";
    }
    
    @PostMapping("/form")
    public String create(User user) {

//        if (result.hasErrors()) {
//            log.error(countryDto.toString());
//            model.addAttribute("regions", regionService.getAll());
//            return "country/form";
//        }

        userService.create(user);
        return "redirect:/user";
    }
    
    @GetMapping("/home")
    public String home(){
        return "layout/home";
    }
  
}
