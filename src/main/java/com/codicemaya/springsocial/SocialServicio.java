/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codicemaya.springsocial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jhovanni <jhovanni@github.com>
 */
@Service
public class SocialServicio implements SocialUserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(SocialServicio.class);
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        log.info("Entry {}", userId);
        Usuario usuario = usuarioRepositorio.findOne(userId);
        if (usuario == null) {
            throw new UsernameNotFoundException(userId);
        }
        return usuario;
    }

}
