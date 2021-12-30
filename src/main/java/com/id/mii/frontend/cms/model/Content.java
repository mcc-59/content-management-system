/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author sihlihis
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Content {
    
    private Long id;
    
    private User user;
    
    private List<Category> categories;
    
    private Type type;
    
    private Status status;
    
    private String title;
    
    private String body;

    public Content(Long id) {
        this.id = id;
    }
}
