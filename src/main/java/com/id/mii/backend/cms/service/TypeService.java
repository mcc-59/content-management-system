/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.service;

import com.id.mii.backend.cms.model.Type;
import com.id.mii.backend.cms.repository.TypeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author USER
 */
@Service
public class TypeService {

    private final TypeRepository typeRepository;

    @Autowired
    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public List<Type> getAll() {
        return typeRepository.findAll();
    }

    public Type getById(Long id) {
        return typeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Type not found"
        ));
    }

    public Type create(Type type) {
        if (type.getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Type already exist");
        }
        return typeRepository.save(type);
    }

    public Type update(Long id, Type type) {
        getById(id);

        type.setCreatedBy(type.getCreatedBy());
        type.setCreatedDate(type.getCreatedDate());
        type.setId(id);

        return typeRepository.save(type);
    }
    
    public Type delete(Long id) {
        Type type = getById(id);
        typeRepository.deleteById(id);

        return type;
    }
}
