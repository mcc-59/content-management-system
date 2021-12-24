/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.service;

import com.id.mii.backend.cms.model.Role;
import com.id.mii.backend.cms.model.User;
import com.id.mii.backend.cms.repository.RoleRepository;
import com.id.mii.backend.cms.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author akhma
 */

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    
    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    
    public List<User> getAll(){
        return userRepository.findAll();
    }
    
    public User getById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> 
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));
    }
    
    public User create(User data){
        Role writerRole = roleRepository.getById(3L);
        
        User user = User.builder()
                .username(data.getUsername())
                .password(data.getPassword())
                .email(data.getEmail())
                .fullName(data.getFullName())
                .isAccountLocked(true)
                .isActive(true)
                .role(writerRole).build();
        
        return userRepository.save(user);
    }
    
    public User update(Long id, User data){
        getById(id);
        data.setId(id);
        return userRepository.save(data);
    }
    
    public User delete(Long id){
        User user = getById(id);
        userRepository.deleteById(id);
        return user;
    }
}
