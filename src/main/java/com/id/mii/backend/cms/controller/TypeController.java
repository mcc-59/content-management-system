/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.controller;

import com.id.mii.backend.cms.model.Type;
import com.id.mii.backend.cms.service.TypeService;
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
@RequestMapping("/type")
@PreAuthorize("hasAnyRole('ADMIN', 'QC')")
public class TypeController {

    private final TypeService typeService;

    @Autowired
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @PreAuthorize("hasAuthority('READ_DATA')")
    @GetMapping
    public ResponseEntity<List<Type>> getAll() {
        return new ResponseEntity(typeService.getAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('READ_DATA')")
    @GetMapping("/{id}")
    public ResponseEntity<Type> getById(@PathVariable("id") Long id) {
        return new ResponseEntity(typeService.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('CREATE_DATA')")
    @PostMapping
    public ResponseEntity<Type> create(@RequestBody Type type) {
        return new ResponseEntity(typeService.create(type), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('UPDATE_DATA')")
    @PutMapping("/{id}")
    public ResponseEntity<Type> update(@PathVariable("id") Long id, @RequestBody Type type) {
        return new ResponseEntity(typeService.update(id, type), HttpStatus.OK);
    }
    
    @PreAuthorize("hasAuthority('DELETE_DATA')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Type> delete(@PathVariable("id") Long id) {
        return new ResponseEntity(typeService.delete(id), HttpStatus.OK);
    }
}
