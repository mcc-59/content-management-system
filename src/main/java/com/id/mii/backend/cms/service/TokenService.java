/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.service;

import com.id.mii.backend.cms.model.Token;
import com.id.mii.backend.cms.model.User;
import com.id.mii.backend.cms.repository.TokenRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author akhma
 */

@Service
public class TokenService {
    private TokenRepository tokenRepository;
    
    @Autowired
    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }
    
    public List<Token> getAll(){
        return tokenRepository.findAll();
    }
    
    public Token getByTokenCode(String tokenCode){
        return tokenRepository.findByTokenCode(tokenCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Token Code Not Found"));
    }
    
    public Token create(User user, int dayExpired){
        UUID uuid = UUID.randomUUID();
        
        LocalDateTime created = LocalDateTime.now();
        LocalDateTime expired = LocalDateTime.now().plusDays(dayExpired);
        
        Token token = Token.builder().tokenCode(uuid.toString()).user(user)
                .created(created).expired(expired).isActive(true).build();
        
        return tokenRepository.save(token);
    }
    
    
}
