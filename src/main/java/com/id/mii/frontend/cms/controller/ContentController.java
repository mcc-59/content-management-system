/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.controller;

import com.id.mii.frontend.cms.model.Content;
import com.id.mii.frontend.cms.model.ContentResponse;
import com.id.mii.frontend.cms.model.User;
import com.id.mii.frontend.cms.model.data.ContentDto;
import com.id.mii.frontend.cms.service.CategoryService;
import com.id.mii.frontend.cms.service.ContentHomeService;
import com.id.mii.frontend.cms.service.ContentService;
import com.id.mii.frontend.cms.service.TypeService;
import com.id.mii.frontend.cms.service.UserService;
import com.id.mii.frontend.cms.utility.FileUploadUtil;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author sihlihis
 */
@Controller
@RequestMapping("/content")
public class ContentController {
    
    private ContentService contentService;
    private ContentHomeService contentHomeService;
    private CategoryService categoryService;
    private TypeService typeService;
    private UserService userService;

    @Autowired
    public ContentController(ContentService contentService, ContentHomeService contentHomeService, CategoryService categoryService, TypeService typeService, UserService userService) {
        this.contentService = contentService;
        this.contentHomeService = contentHomeService;
        this.categoryService = categoryService;
        this.typeService = typeService;
        this.userService = userService;
    }
    
    @GetMapping
    public String index(Model model) {
        model.addAttribute("contents", contentService.getAll());
        model.addAttribute("types", typeService.getAll());
        model.addAttribute("categories", categoryService.getAll());
        return "content/index";
    }


    @GetMapping("/form/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("content", contentService.getById(id));
        return "content/update-form";
    }
    
    @GetMapping("/getallcontents")
    @ResponseBody
    public List<ContentResponse> getAllContentByWriters() {
        return contentService.getAll();
    }
    
    @GetMapping("/getallcontents/{id}")
    @ResponseBody
    public List<Content> getAllContentByWriter(@PathVariable("id") Long id){
        return contentService.getContentByWriter(id);
    }
    
    @GetMapping("get-all")
    @ResponseBody
    public List<Content> getAll() {
        return contentHomeService.getFirstTen();
    }
    
    @GetMapping("get-trending")
    @ResponseBody
    public Content getTrending() {
        return contentHomeService.getTrending();
    }

    @PutMapping("/form/{id}")
    public String update(@PathVariable("id") Long id, Content content) {
        contentService.update(id, content);
        return "redirect:/content";
    }
}
