/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.controller;

import com.id.mii.backend.cms.model.Content;
import com.id.mii.backend.cms.service.ContentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author akhma
 */

@RestController
@RequestMapping("/content-home")
public class ContentHomeController {
    
    private ContentService contentService;
    
    @Autowired
    public ContentHomeController(ContentService contentService) {
        this.contentService = contentService;
    }
    
    @GetMapping
    public ResponseEntity<List<Content>> getTopTen() {
        return new ResponseEntity(contentService.findTopTen(), HttpStatus.OK);
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<List<Content>> getByCategory(@PathVariable("id") Long categoryId) {
        return new ResponseEntity(contentService.findByCategory(categoryId), HttpStatus.OK);
    }
    
    @GetMapping("/trending")
    public ResponseEntity<Content> getTrending() {
        return new ResponseEntity(contentService.findTrending(), HttpStatus.OK);
    }
    
    @GetMapping("/view")
    public ResponseEntity<List<Content>> listByView() {
        return new ResponseEntity(contentService.listByView(), HttpStatus.OK);
    }
    
    
}
