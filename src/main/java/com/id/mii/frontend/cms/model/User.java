/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.model;

import javax.validation.constraints.NotEmpty;
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
public class User {
    
    private Long id;
    
    @NotEmpty(message = "Username cannot be empty!")
    private String username;
    
    @NotEmpty(message = "Email cannot be empty!")
    private String email;
    
    @NotEmpty(message = "Full Name cannot be empty!")
    private String fullName;
    
}
