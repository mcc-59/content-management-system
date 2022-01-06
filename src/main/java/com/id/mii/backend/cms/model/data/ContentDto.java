/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.model.data;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author USER
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentDto {
    
    private Long user;
    
    private List<Long> categories;
    
    private Long type;
    
    private String title;
    
    private String body;
    
    private List<String> medias;
    
    private Long views;
    
    private Boolean isLocked;
}
