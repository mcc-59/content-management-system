/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.repository;

import com.id.mii.backend.cms.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author akhma
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByUsername(String username);
    
    @Query(value = "SELECT * FROM tb_user WHERE role_id = 3 AND deleted IS NULL", nativeQuery = true)
    List<User> listWriter();
    
}
