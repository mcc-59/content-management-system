/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.controller;

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
    
    @GetMapping("/dashboard")
    public String contentWriterDashboard() {
        return "writer/index";
    }
    
    @GetMapping("/newcontent")
    public String addContent() {
        return "writer/new-content";
    }
    
    @GetMapping("/contenthistory")
    public String contentHistory() {
        return "writer/content-history";
    }
    
    @GetMapping("/qc/dashboard")
    public String qualityControlDashboard() {
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
