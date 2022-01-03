/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.repository;

import com.id.mii.backend.cms.model.Content;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author USER
 */
@Repository
public interface ContentRepository extends JpaRepository<Content, Long>{
    
    @Query(value = "SELECT * FROM tb_content AS c WHERE c.id IN (SELECT content_id FROM tb_content_category WHERE category_id = ?1) ORDER BY publish_date;", nativeQuery = true)
    List<Content> findContentByCategory(Long categoryId);
    
    @Query(value = "SELECT * FROM tb_content WHERE views = (SELECT MAX(views) FROM tb_content) LIMIT 1;", nativeQuery = true)
    Content findTrendingContent();
    
    @Query(value = "SELECT * FROM tb_content ORDER BY publish_date LIMIT 10;", nativeQuery = true)
    List<Content> findFirstTen();
}
    
