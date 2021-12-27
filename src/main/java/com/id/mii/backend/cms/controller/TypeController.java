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
public class TypeController {

    private final TypeService typeService;

    @Autowired
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping
    public ResponseEntity<List<Type>> getAll() {
        return new ResponseEntity(typeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Type> getById(@PathVariable("id") Long id) {
        return new ResponseEntity(typeService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Type> create(@RequestBody Type type) {
        return new ResponseEntity(typeService.create(type), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Type> update(@PathVariable("id") Long id, @RequestBody Type type) {
        return new ResponseEntity(typeService.update(id, type), HttpStatus.OK);
    }
}
