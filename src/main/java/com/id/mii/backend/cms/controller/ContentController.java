/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.controller;

import com.id.mii.backend.cms.model.Content;
import com.id.mii.backend.cms.model.data.ContentDto;
import com.id.mii.backend.cms.service.ContentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author USER
 */
@RestController
@RequestMapping("/content")
@PreAuthorize("hasAnyRole('ADMIN', 'QC', 'WRITER')")
public class ContentController {

    private final ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @PreAuthorize("hasAuthority('READ_DATA')")
    @GetMapping
    public ResponseEntity<List<Content>> getAll() {
        return new ResponseEntity(contentService.getAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('READ_DATA')")
    @GetMapping("/{id}")
    public ResponseEntity<Content> getById(@PathVariable("id") Long id) {
        return new ResponseEntity(contentService.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('CREATE_DATA')")
    @PostMapping
    public ResponseEntity<Content> create(@RequestBody ContentDto contentDto) {
        return new ResponseEntity(contentService.create(contentDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('UPDATE_DATA')")
    @PutMapping("/{id}")
    public ResponseEntity<Content> update(@PathVariable("id") Long id, @RequestBody Content content) {
        return new ResponseEntity(contentService.update(id, content), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('DELETE_DATA')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Content> delete(@PathVariable("id") Long id) {
        return new ResponseEntity(contentService.delete(id), HttpStatus.OK);
    }
}
