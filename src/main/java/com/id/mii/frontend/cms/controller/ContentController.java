/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.controller;

import com.id.mii.frontend.cms.model.Content;
import com.id.mii.frontend.cms.model.data.CategoryDto;
import com.id.mii.frontend.cms.service.CategoryService;
import com.id.mii.frontend.cms.service.ContentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author sihlihis
 */
@Controller
@RequestMapping("/content")
public class ContentController {
    
    private ContentService contentService;
    private CategoryService categoryService;

    public ContentController(ContentService contentService, CategoryService categoryService) {
        this.contentService = contentService;
        this.categoryService = categoryService;
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
    public String form(Content content, Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "content/form";
    }

    @PostMapping("/form")
    public String create(Content content) {
        System.out.println(content);
        contentService.create(content);
        return "redirect:/content";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, Content content) {
        contentService.update(id, content);
        return "redirect:/content";
    }
}
