/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.controller;

import com.id.mii.frontend.cms.model.data.ContentDto;
import com.id.mii.frontend.cms.service.ContentHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author USER
 */
@Controller
@RequestMapping("/")
public class HomeController {
    
    private ContentHomeService contentHomeService;
    
    @Autowired
    public HomeController(ContentHomeService contentHomeService) {
        this.contentHomeService = contentHomeService;
    }
    
    @GetMapping
    public String home(Model model, ContentDto categoryDto) {
        model.addAttribute("trending", contentHomeService.getTrending());
        model.addAttribute("topTen", contentHomeService.getFirstTen());
        return "index";
    }
}
