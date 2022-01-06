/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.controller;

import com.id.mii.frontend.cms.model.Content;
import com.id.mii.frontend.cms.service.ContentHomeService;
import com.id.mii.frontend.cms.service.DashboardDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author sihlihis
 */
@Controller
public class DashboardController {
    private DashboardDetailService dashboardDetailService;
    private ContentHomeService contentHomeService;
    
    @Autowired
    public DashboardController(DashboardDetailService dashboardDetailService, ContentHomeService contentHomeService) {
        this.dashboardDetailService = dashboardDetailService;
        this.contentHomeService = contentHomeService;
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
    public String addContent(Content content) {
        return "writer/new-content";
    }
    
    @GetMapping("/contenthistory")
    public String contentHistory() {
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
    public String manageContent() {
        return "qc/manage-content";
    }
    
    @GetMapping("/qc/managewriter")
    public String manageWriter() {
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
