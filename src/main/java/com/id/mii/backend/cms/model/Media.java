/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "tb_media")
@SQLDelete(sql = "UPDATE tb_media SET deleted = CURRENT_TIMESTAMP WHERE id=?")
@Where(clause = "deleted IS NULL")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Media extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_path", nullable = false)
    private String path;

    @ManyToOne
    private Content content;
    
    @Column(nullable = true)
    private Timestamp deleted;
}
