/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codicemaya.springsocial.controlador;

import com.codicemaya.springsocial.Usuario;
import java.security.Principal;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jhovanni <jhovanni@github.com>
 */
@RestController
@RequestMapping("/api/session")
public class SesionControlador {

    protected static final Logger log = LoggerFactory.getLogger(SesionControlador.class);
    @Autowired
    AuthenticationManager authenticationManager;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> session(Principal principal) {
        log.info("Entry {}", principal);
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Usuario usuario = new Usuario();
        usuario.setName(principal.getName());
        return ResponseEntity.ok(usuario);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void logout(HttpSession session) {
        session.invalidate();
    }
}
