package com.bohniman.cid.appapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 */
@RestController
@RequestMapping("/api")
public class UserController {

    
    @RequestMapping(value="/hello", method = RequestMethod.GET)
    public String getHello() {
        return "Hello World";
    }
    
}