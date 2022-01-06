/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.controller;

import com.id.mii.frontend.cms.model.Content;
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
        model.addAttribute("types", typeService.getAll());
        return "content/form";
    }
    
    @GetMapping("get-all")
    @ResponseBody
    public List<ContentDto> getAll() {
        return contentHomeService.getFirstTen();
    }
    
    @GetMapping("get-trending")
    @ResponseBody
    public ContentDto getTrending() {
        return contentHomeService.getTrending();
    }

    @PostMapping("/form")
    public String create(ContentDto content, @RequestParam("image") MultipartFile multipartFile) throws IOException{
        content.setUser(2L);
        
        List<String> fileName = new ArrayList<>();
        fileName.add(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
        content.setMedias(fileName);
        
        Content savedContent = contentService.create(content);
        System.out.println(savedContent);
        
        String uploadDir = "content-images/" + savedContent.getId();
        System.out.println(uploadDir);
        
        FileUploadUtil.saveFile(uploadDir, fileName.get(0), multipartFile);
                
        return "redirect:/content";
    }

    @PutMapping("/form/{id}")
    public String update(@PathVariable("id") Long id, Content content) {
        contentService.update(id, content);
        return "redirect:/content";
    }
}
