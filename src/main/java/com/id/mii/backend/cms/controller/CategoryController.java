/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.controller;

import com.id.mii.backend.cms.model.Category;
import com.id.mii.backend.cms.service.CategoryService;
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
@RequestMapping("/category")
@PreAuthorize("hasAnyRole('ADMIN', 'QC', 'WRITER')")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasAuthority('READ_DATA')")
    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        return new ResponseEntity(categoryService.getAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('READ_DATA')")
    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable("id") Long id) {
        return new ResponseEntity(categoryService.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('CREATE_DATA')")
    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        return new ResponseEntity(categoryService.create(category), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('UPDATE_DATA')")
    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable("id") Long id, @RequestBody Category category) {
        return new ResponseEntity(categoryService.update(id, category), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('DELETE_DATA')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable("id") Long id) {
        return new ResponseEntity(categoryService.delete(id), HttpStatus.OK);
    }
}
