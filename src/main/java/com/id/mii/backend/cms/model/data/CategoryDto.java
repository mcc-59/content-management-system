/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.model.data;

import com.id.mii.backend.cms.model.Type;
import com.id.mii.backend.cms.model.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
public class CategoryDto {
    
    private Long id;
    
    private User user;
    
    private List<String> categories = new ArrayList<>();
    
    private Type type;
    
    private String title;
    
    private String body;
    
    private LocalDateTime publishDate;
    
    private List<String> medias = new ArrayList<>();
    
    private Long views;
    
    private Boolean isLocked;
}
