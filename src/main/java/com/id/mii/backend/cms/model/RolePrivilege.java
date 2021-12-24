/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.model;

import com.id.mii.backend.cms.model.key.RolePrivilegeKey;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
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
@Table(name = "tb_role_privilege")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolePrivilege {
    @EmbeddedId
    private RolePrivilegeKey id;
    
    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Role role;
    
    @ManyToOne
    @MapsId("privilegeId")
    @JoinColumn(name = "privilege_id")
    private Privilege privilege;
    
}
