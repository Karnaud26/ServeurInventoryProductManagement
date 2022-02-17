package com.sipm.invm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserRestController {

    @RequestMapping("/api/users/authenticate")
    public Principal user(Principal user){
        return user;
    }
}
