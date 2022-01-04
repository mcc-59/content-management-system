/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.controller;

import com.id.mii.frontend.cms.model.Content;
import com.id.mii.frontend.cms.model.data.CategoryDto;
import com.id.mii.frontend.cms.service.ContentHomeService;
import com.id.mii.frontend.cms.service.ContentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author sihlihis
 */
@Controller
@RequestMapping("/content")
public class ContentController {
    
    private ContentService contentService;
    private ContentHomeService contentHomeService;
    
    @Autowired
    public ContentController(ContentService contentService, ContentHomeService contentHomeService) {
        this.contentService = contentService;
        this.contentHomeService = contentHomeService;
    }
    
    @GetMapping
    public String index(Model model) {
        model.addAttribute("contents", contentService.getAll());
        return "content/index";
    }


    @GetMapping("/form/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("content", contentService.getById(id));
        return "content/update-form";
    }

    @GetMapping("/form")
    public String form(Content content) {
        return "content/form";
    }
    
    @GetMapping("get-all")
    @ResponseBody
    public List<CategoryDto> getAll() {
        return contentHomeService.getFirstTen();
    }
    
    @GetMapping("get-trending")
    @ResponseBody
    public CategoryDto getTrending() {
        return contentHomeService.getTrending();
    }

    @PostMapping
    public String create(Content content) {
        contentService.create(content);
        return "redirect:/content";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, Content content) {
        contentService.update(id, content);
        return "redirect:/content";
    }
}
