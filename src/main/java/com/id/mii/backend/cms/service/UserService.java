/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.service;

import com.id.mii.backend.cms.config.AppSecurityConfig;
import com.id.mii.backend.cms.model.Role;
import com.id.mii.backend.cms.model.Token;
import com.id.mii.backend.cms.model.User;
import com.id.mii.backend.cms.repository.RoleRepository;
import com.id.mii.backend.cms.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import com.id.mii.backend.cms.repository.DashboardDetailRepository;

/**
 *
 * @author akhma
 */

@Service
@Slf4j
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private TokenService tokenService;
    private JavaMailSender javaMailSender;
    private SpringTemplateEngine templateEngine;
    private AppSecurityConfig appSecurityConfig;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, TokenService tokenService, JavaMailSender javaMailSender, SpringTemplateEngine templateEngine, AppSecurityConfig appSecurityConfig) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.tokenService = tokenService;
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.appSecurityConfig = appSecurityConfig;
    }
    
    public List<User> getAll(){
        return userRepository.findAll();
    }
    
    public List<User> findWriter(){
        return userRepository.listWriter();
    }
    
    public User getById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> 
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));
    }
    
    public User create(User data){
        Role writerRole = roleRepository.getById(3L);
        
        String encrypt = appSecurityConfig.passwordEncoder().encode(data.getPassword());
        
        //SET USER
        User user = User.builder()
                .username(data.getUsername())
                .password(encrypt)
                .email(data.getEmail())
                .fullName(data.getFullName())
                .isAccountLocked(true)
                .role(writerRole).build();
        userRepository.save(user);
        log.info(user.getRole().getName());
        //GENERATE TOKEN
        Token token = tokenService.create(user, 3);
        
        //SEND EMAIL
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
            String messageInput = "By clicking on the link below, you can activate your account";
            String message = templateEngine.process("EmailUpdate", buildContext(messageInput, user.getUsername(), token.getTokenCode()));
            messageHelper.setTo(user.getEmail());
            messageHelper.setSubject("PaperLink [Set your new password]");
            messageHelper.setText(message, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException | MailException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
        
        return user;
    }
    
    public User update(Long id, User data){
        User user = getById(id);
        user.setEmail(data.getEmail());
        user.setFullName(data.getFullName());
        user.setUsername(data.getUsername());
        return userRepository.save(user);
    }
    
    public User delete(Long id){
        User user = getById(id);
        userRepository.deleteById(id);
        return user;
    }
    
    public User updatePassword(String tokenCode, User user) {
        Token token = tokenService.getByTokenCode(tokenCode);
        
        User user1 = userRepository.findByUsername(token.getUser().getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden access"));
        
        
        LocalDateTime dateNow = LocalDateTime.now();
        if (token.getConfirm() == null) {
            if (dateNow.isBefore(token.getExpired())) {
            token.setConfirm(dateNow);
            token.setIsActive(false);
            String encrypt = appSecurityConfig.passwordEncoder().encode(user.getPassword());
            user1.setPassword(encrypt);
            user1.setIsAccountLocked(false);
            return userRepository.save(user1);
            }
        }       
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Link expired");
    }
    
    public User activate(String tokenCode){
        Token token = tokenService.getByTokenCode(tokenCode);
        
        User user1 = userRepository.findByUsername(token.getUser().getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden access"));
        
        
        LocalDateTime dateNow = LocalDateTime.now();
        if (token.getConfirm() == null) {
            if (dateNow.isBefore(token.getExpired())) {
            token.setConfirm(dateNow);
            token.setIsActive(false);
            user1.setIsAccountLocked(false);
            return userRepository.save(user1);
            }
        }       
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Link expired");
    }
    
    private Context buildContext(String message, String username, String token) {
        Context context = new Context();
        context.setVariable("hello", "Hello, " + username + " !");
        context.setVariable("message", message);
        context.setVariable("token", "http://localhost:8089/profile/activate/" + token);

        return context;
    }
}
