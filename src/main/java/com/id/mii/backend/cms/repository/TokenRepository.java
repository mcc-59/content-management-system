/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.repository;

import com.id.mii.backend.cms.model.Token;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author akhma
 */
@Repository
public interface TokenRepository extends JpaRepository<Token, Long>{
    Optional<Token> findByUser_Id(Long id);
    Optional<Token> findByTokenCode(String tokenCode);
}
