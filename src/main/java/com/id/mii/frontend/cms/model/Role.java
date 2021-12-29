/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author akhma
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
    
    private Long id;
    
    private String name;

    public Role(Long id) {
        this.id = id;
    }
    
}
