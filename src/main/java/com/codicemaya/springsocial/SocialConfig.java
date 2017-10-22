/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codicemaya.springsocial;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.web.SignInAdapter;

/**
 *
 * @author Jhovanni <jhovanni@github.com>
 */
@Configuration
public class SocialConfig {

    @Bean
    public SignInAdapter authSignInAdapter() {
        return (userId, connection, request) -> {
            AuthUtil.authenticate(connection);
            return null;
        };
    }
}
