/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author akhma
 */

@Controller
public class ErrorsController implements ErrorController{
    
    @RequestMapping("/error")
    public String handlError() {
        return "error";
    }
    
}
