/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codicemaya.springsocial.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.github.connect.GitHubConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;

/**
 *
 * @author Jhovanni <jhovanni@github.com>
 */
//@Component
@Configuration
@EnableSocial
class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private RegistroSocial registroSocial;
    @Value("${google.appId}")
    private String googleId;
    @Value("${google.appSecret}")
    private String googleSecret;
    @Value("${github.appId}")
    private String githubId;
    @Value("${github.appSecret}")
    private String githubSecret;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        connectionFactoryConfigurer
                .addConnectionFactory(new GoogleConnectionFactory(googleId, googleSecret));
        connectionFactoryConfigurer
                .addConnectionFactory(new GitHubConnectionFactory(githubId, githubSecret));
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        TextEncryptor textEncryptor = Encryptors.noOpText();
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, textEncryptor);
        repository.setConnectionSignUp(registroSocial);
        return repository;
    }

    @Bean
    public ConnectController connectController(
            ConnectionFactoryLocator connectionFactoryLocator,
            ConnectionRepository connectionRepository) {
        return new ConnectController(connectionFactoryLocator, connectionRepository);
    }
}
