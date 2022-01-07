/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.repository;



import com.id.mii.backend.cms.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author akhma
 */
@Repository
public interface DashboardDetailRepository extends JpaRepository<Content, Long> {

    @Query(value = "SELECT COUNT(*) FROM tb_user WHERE role_id = 3", nativeQuery = true)
    Integer countWriter();

    @Query(value = "SELECT COUNT(*) FROM tb_content WHERE tb_content.status_id = 3", nativeQuery = true)
    Integer countContent();

    @Query(value = "SELECT SUM(views) FROM tb_content WHERE tb_content.status_id=3", nativeQuery = true)
    Integer countViews();

    @Query(value = "SELECT COUNT(*) FROM tb_content WHERE tb_content.status_id = 1", nativeQuery = true)
    Integer countPending();

    @Query(value = "SELECT COUNT(*) FROM tb_content WHERE tb_content.status_id = 1", nativeQuery = true)
    Integer countRejected();

    
}
