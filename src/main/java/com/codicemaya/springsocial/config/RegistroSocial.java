/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codicemaya.springsocial.config;

import com.codicemaya.springsocial.Usuario;
import com.codicemaya.springsocial.UsuarioRepositorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jhovanni <jhovanni@github.com>
 */
@Component
class RegistroSocial implements ConnectionSignUp {

    private static final Logger LOG = LoggerFactory.getLogger(RegistroSocial.class);

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public String execute(Connection<?> connection) {
        ConnectionKey key = connection.getKey();
        UserProfile profile = connection.fetchUserProfile();
        String nombre = profile.getUsername();
        Usuario usuario = new Usuario();
        usuario.setName(nombre);
        usuarioRepositorio.save(usuario);
        return nombre;
    }

}
