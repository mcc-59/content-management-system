/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.model;

import java.time.LocalDateTime;
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
    
    private Status status;
    
    private LocalDateTime publishDate;
    
    private LocalDateTime expiredDate;
    
    private Type type;
    
    private String title;
    
    private String body;
    
    private List<Media> medias;
    
    private List<Category> categories;
    
    private Long views;
    
    private Boolean isLocked;
}
