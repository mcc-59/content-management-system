/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author akhma
 */

@Entity
@Table(name = "tb_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    
    @Id
    @Column(name = "id")
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(name = "full_name", nullable = false)
    private String fullName;
    
    @Column(nullable = false)
    private boolean isAccountLocked;
    
    @Column(nullable = false)
    private boolean isActive;
    
    @ManyToOne
    private Role role;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Token> tokens;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Content> contents;
    
    
    
}
