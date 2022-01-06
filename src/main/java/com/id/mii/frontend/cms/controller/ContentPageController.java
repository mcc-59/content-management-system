/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.controller;

import com.id.mii.frontend.cms.model.Content;
import com.id.mii.frontend.cms.model.data.ContentDto;
import com.id.mii.frontend.cms.service.CategoryService;
import com.id.mii.frontend.cms.service.ContentHomeService;
import com.id.mii.frontend.cms.service.ContentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author akhma
 */

@Controller
@RequestMapping("/page")
public class ContentPageController {
    private CategoryService categoryService;
    private ContentHomeService contentHomeService;
    private ContentService contentService;
    
    @Autowired
    public ContentPageController(CategoryService categoryService, ContentHomeService contentHomeService, ContentService contentService) {
        this.categoryService = categoryService;
        this.contentHomeService = contentHomeService;
        this.contentService = contentService;
    }
      
    
    @GetMapping("category/{id}")
    public String getCategory(Model model, @PathVariable("id") Long id) {
        model.addAttribute("title", categoryService.getById(id));
        model.addAttribute("contents", contentHomeService.getByCategoryId(id));
        return "category";
    }

    @GetMapping("/content/{id}")
    public String getContent(Model model, @PathVariable("id") Long id) {
        model.addAttribute("content", contentService.getById(id));
        return "contentHome";
    }
    
    @GetMapping("/content-category/{id}")
    @ResponseBody
    public List<Content> getContentCategory(@PathVariable("id") Long id) {
        return contentHomeService.getByCategoryId(id);
    }
}
