/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.controller;

import com.id.mii.backend.cms.model.User;
import com.id.mii.backend.cms.service.UserService;
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
 * @author akhma
 */

@RestController
@RequestMapping("/user")
@PreAuthorize("hasAnyRole('ADMIN')")
public class UserController {
    private UserService userService;
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping
    @PreAuthorize("hasAuthority('READ_DATA')")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity(userService.getAll(), HttpStatus.OK);
    }
    
    @PreAuthorize("hasAuthority('READ_DATA')")
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id) {
        return new ResponseEntity(userService.getById(id), HttpStatus.OK);
    }
    
    @PreAuthorize("hasAuthority('CREATE_DATA')")
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        return new ResponseEntity(userService.create(user), HttpStatus.OK);
    }
    
    @PreAuthorize("hasAuthority('UPDATE_DATA')")
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody User user) {
        return new ResponseEntity(userService.update(id, user), HttpStatus.OK);
    }
    
    @PreAuthorize("hasAuthority('DELETE_DATA')")
    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") Long id) {
        return new ResponseEntity(userService.delete(id), HttpStatus.OK);
    }
    
}
