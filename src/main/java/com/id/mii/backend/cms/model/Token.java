/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.model;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author akhma
 */

@Entity
@Table(name = "tb_token")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "confirm_date")
    private LocalDateTime confirm;
    
    @Column(name = "created_date")
    private LocalDateTime created;
    
    @Column(name = "expired_date")
    private LocalDateTime expired;
    
    @Column(nullable = false)
    private String tokenCode;
    
    @ManyToOne
    private User user;
    
    private Boolean isActive;
}
