/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codicemaya.springsocial;

import java.security.Principal;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AuthenticationResource {

    protected static final Logger log = LoggerFactory.getLogger(AuthenticationResource.class);
    @Autowired
    AuthenticationManager authenticationManager;

    @RequestMapping(method = RequestMethod.GET)
    public User session(Principal user) {
        log.info("Entry {}", user);
        String name = user == null ? null : user.getName();
        return new User(name);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void logout(HttpSession session) {
        session.invalidate();
    }
}
