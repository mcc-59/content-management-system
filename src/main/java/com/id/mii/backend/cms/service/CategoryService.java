/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.service;

import com.id.mii.backend.cms.model.Category;
import com.id.mii.backend.cms.repository.CategoryRepository;
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
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Category not found"
        ));
    }

    public Category create(Category category) {
        if (category.getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category already exist");
        }
        return categoryRepository.save(category);
    }
    
    public Category update(Long id, Category category) {
        getById(id);
        
        category.setId(id);
        
        return categoryRepository.save(category);
    }
    
    public Category delete(Long id) {
        Category category = getById(id);
        categoryRepository.deleteById(id);
        
        return category;
    }
}
