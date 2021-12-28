/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.service;

import com.id.mii.backend.cms.model.Role;
import com.id.mii.backend.cms.model.User;
import com.id.mii.backend.cms.repository.RoleRepository;
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
public class RoleService {
    private RoleRepository roleRepository;
    
    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
    public List<Role> getAll(){
        return roleRepository.findAll();
    }
    
    public Role getById(Long id){
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role Not Found"));
    }
    
    public Role create(Role data){
        return roleRepository.save(data);
    }
    
    public Role update(Long id, Role data){
        Role role = getById(id);
        data.setCreatedBy(role.getCreatedBy());
        data.setCreatedDate(role.getCreatedDate());
        data.setId(id);
        return roleRepository.save(data);
    }
    
    public Role delete(Long id){
        Role role = getById(id);
        roleRepository.deleteById(id);
        return role;
    }
    
    
    
    
}
