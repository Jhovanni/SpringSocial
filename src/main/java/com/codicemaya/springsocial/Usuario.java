/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codicemaya.springsocial;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.social.security.SocialUser;

/**
 *
 * @author Jhovanni <jhovanni@github.com>
 */
public class Usuario extends SocialUser {

    private String name;

    public Usuario(String name) {
        super(name, name, AuthorityUtils.NO_AUTHORITIES);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
