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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jhovanni <jhovanni@github.com>
 */
@RestController
@RequestMapping("/api/session")
public class SesionControlador {

    /**
     * TODO:manejar cuando el proveedor no da acceso, redirecciona a signin,
     * seguro se puede configuar en algún lado
     */
    protected static final Logger log = LoggerFactory.getLogger(SesionControlador.class);
    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping
    public ResponseEntity<?> session(Principal principal) {
        log.info("Entry {}", principal);
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{}");
        }
        Usuario usuario = new Usuario();
        usuario.setName(principal.getName());
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
