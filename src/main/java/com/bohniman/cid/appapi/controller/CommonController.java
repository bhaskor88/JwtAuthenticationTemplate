package com.bohniman.cid.appapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CommonController
 */
@RestController
public class CommonController {

    @RequestMapping({ "/Hello" })
    public String getHello() {
        return "Hello World";
    }

}