/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.utility;

import java.nio.charset.Charset;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author akhma
 */
public class BasicHeader {
   
    public static String createBasicToken(String username, String password){
        String auth = username + ":" + password;
        byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        return new String(encodeAuth);
    }
    
    public static HttpHeaders createBasicHeader(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return new HttpHeaders(){{
            set( "Authorization", "Basic " + auth.getCredentials());
        }};
    }
}
