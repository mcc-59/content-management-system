/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.model.data;

import com.id.mii.backend.cms.model.Category;
import com.id.mii.backend.cms.model.Media;
import com.id.mii.backend.cms.model.Status;
import com.id.mii.backend.cms.model.Type;
import com.id.mii.backend.cms.model.User;
import java.time.LocalDateTime;
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
    
    private User user;
    
    private Status status;
    
    private LocalDateTime publishDate;
    
    private LocalDateTime expiredDate;
    
    private List<Category> categories;
    
    private Type type;
    
    private String title;
    
    private String body;
    
    private List<Media> medias;
    
    private Long views;
    
    private boolean isLocked;
}
