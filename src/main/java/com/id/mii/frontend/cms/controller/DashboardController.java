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
import com.id.mii.frontend.cms.service.DashboardDetailService;
import com.id.mii.frontend.cms.service.TypeService;
import com.id.mii.frontend.cms.service.UserService;
import com.id.mii.frontend.cms.utility.FileUploadUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author sihlihis
 */
@Controller
public class DashboardController {
    private DashboardDetailService dashboardDetailService;
    private ContentHomeService contentHomeService;
    private CategoryService categoryService;
    private TypeService typeService;
    private ContentService contentService;
    private UserService userService;

    @Autowired
    public DashboardController(DashboardDetailService dashboardDetailService, ContentHomeService contentHomeService, CategoryService categoryService, TypeService typeService, ContentService contentService, UserService userService) {
        this.dashboardDetailService = dashboardDetailService;
        this.contentHomeService = contentHomeService;
        this.categoryService = categoryService;
        this.typeService = typeService;
        this.contentService = contentService;
        this.userService = userService;
    }
    
    @GetMapping("/dashboard")
    public String contentWriterDashboard(Model model) {
        model.addAttribute("totalContent", dashboardDetailService.totalContent());
        model.addAttribute("totalView", dashboardDetailService.totalView());
        model.addAttribute("totalPending", dashboardDetailService.totalPending());
        model.addAttribute("totalReject", dashboardDetailService.totalReject());
        model.addAttribute("topTen", contentHomeService.getFirstTen());
        model.addAttribute("topView", contentHomeService.getTopView());
        return "writer/index";
    }
    
    @GetMapping("/newcontent")
    public String newContent(Content content, Model model) {
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("types", typeService.getAll());
        return "writer/new-content";
    }
    
    @PostMapping("/newcontent")
    public String create(ContentDto content, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        content.setUser(3L);
        System.out.println(content);
        List<String> fileName = new ArrayList<>();
        fileName.add(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
        content.setMedias(fileName);
        
        Content savedContent = contentService.create(content);
        
        String uploadDir = "./src/main/resources/static/image/" + savedContent.getId();
        
        System.out.println(uploadDir);
        
        FileUploadUtil.saveFile(uploadDir, fileName.get(0), multipartFile);
                
        return "redirect:/contenthistory";
    }
    
    @GetMapping("/contenthistory/{id}")
    public String contentHistory(Model model, @PathVariable("id") Long userId) {
//        model.addAttribute("contents", contentService.getContentByWriter(userId));
        return "writer/content-history";
    }
    
    @GetMapping("/qc/dashboard")
    public String qualityControlDashboard(Model model) {
        model.addAttribute("totalContent", dashboardDetailService.totalContent());
        model.addAttribute("totalView", dashboardDetailService.totalView());
        model.addAttribute("totalPending", dashboardDetailService.totalPending());
        model.addAttribute("totalWriter", dashboardDetailService.totalWriter());
        model.addAttribute("topTen", contentHomeService.getFirstTen());
        model.addAttribute("topView", contentHomeService.getTopView());
        return "qc/index";
    }
    
    @GetMapping("/qc/managecontent")
    public String manageContent(Model model) {
        model.addAttribute("contents", contentService.getAll());
        return "qc/manage-content";
    }
    
    @GetMapping("/qc/managewriter")
    public String manageWriter(Model model) {
        model.addAttribute("writers", userService.findWriter());
        return "qc/manage-writer";
    }
    
    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin/index";
    }
    
    @GetMapping("/admin/manageuser")
    public String manageUser() {
        return "admin/manage-user";
    }
}
