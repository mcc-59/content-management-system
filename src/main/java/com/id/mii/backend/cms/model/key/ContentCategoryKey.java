/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.model.key;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author USER
 */
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContentCategoryKey implements Serializable {
    
    @Column(name = "content_id")
    private Long contentId;

    @Column(name = "category_id")
    private Long categoryId;
}
