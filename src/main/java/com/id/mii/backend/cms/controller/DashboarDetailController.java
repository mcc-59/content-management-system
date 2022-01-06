/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.controller;



import com.id.mii.backend.cms.service.DashboardDetailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author akhma
 */

@RestController
@RequestMapping("/detail")
public class DashboarDetailController {
    private DashboardDetailService dashboardDetailService;
    
    @Autowired
    public DashboarDetailController(DashboardDetailService dashboardDetailService) {
        this.dashboardDetailService = dashboardDetailService;
    }
    
    @GetMapping("/writer")
    public ResponseEntity<Integer> countWriter(){
        return new ResponseEntity(dashboardDetailService.countWriter(), HttpStatus.OK);
    }
    @GetMapping("/content")
    public ResponseEntity<Integer> countContent(){
        return new ResponseEntity(dashboardDetailService.countContent(), HttpStatus.OK);
    }
    @GetMapping("/view")
    public ResponseEntity<Integer> countView(){
        return new ResponseEntity(dashboardDetailService.countView(), HttpStatus.OK);
    }
    @GetMapping("/pending")
    public ResponseEntity<Integer> countPending(){
        return new ResponseEntity(dashboardDetailService.countPending(), HttpStatus.OK);
    }
    @GetMapping("/reject")
    public ResponseEntity<Integer> countReject(){
        return new ResponseEntity(dashboardDetailService.countReject(), HttpStatus.OK);
    }
}
