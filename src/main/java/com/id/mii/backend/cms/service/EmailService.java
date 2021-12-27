/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.service;

import com.id.mii.backend.cms.model.Token;
import com.id.mii.backend.cms.model.User;
import com.id.mii.backend.cms.model.data.EmailSenderDto;
import com.id.mii.backend.cms.repository.UserRepository;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

/**
 *
 * @author akhma
 */
@Service
public class EmailService {

    private JavaMailSender javaMailSender;
    private UserRepository userRepository;
    private SpringTemplateEngine templateEngine;
    private TokenService tokenService;

    @Autowired
    public EmailService(JavaMailSender javaMailSender, UserRepository userRepository, SpringTemplateEngine templateEngine, TokenService tokenService) {
        this.javaMailSender = javaMailSender;
        this.userRepository = userRepository;
        this.templateEngine = templateEngine;
        this.tokenService = tokenService;
    }

    public EmailSenderDto sendEmailForget(EmailSenderDto data) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
            User user = userRepository.findByUsernameOrEmail(data.getTo(), data.getTo())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email is not valid"));

            Token token = tokenService.create(user, 1);

            String messageInput = "Please change your password via the following link";

            String message = templateEngine.process("EmailForget", buildContextForget(messageInput, user.getUsername(), token.getTokenCode()));

            messageHelper.setTo(user.getEmail());
            messageHelper.setSubject("Paperlink [Forget Password]");
            messageHelper.setText(message, true);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException | MailException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
        return data;
    }

    private Context buildContextForget(String message, String username, String token) {
        Context context = new Context();
        context.setVariable("hello", "Hello, " + username + " !");
        context.setVariable("message", message);
        context.setVariable("token", "http://localhost:8087/api/v1/user/update" + token);

        return context;
    }

}
