/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.controller;

import com.id.mii.backend.cms.model.Role;
import com.id.mii.backend.cms.service.RoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author akhma
 */

@RestController
@RequestMapping("/role")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {
    private RoleService roleService;
    
    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    
    @GetMapping
    public ResponseEntity<List<Role>> getAll(){
        return new ResponseEntity(roleService.getAll(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Role> getById(@PathVariable("id") Long id){
        return new ResponseEntity(roleService.getById(id), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Role> create(@RequestBody Role role){
        return new ResponseEntity(roleService.create(role), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Role> update(@PathVariable("id") Long id, @RequestBody Role role) {
        return new ResponseEntity(roleService.update(id, role), HttpStatus.OK);
    }
    
    
}
