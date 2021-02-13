package com.li.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DBAController {

    @GetMapping("/db/hello")
    public String dba(){
        return "hello dba";
    }
}
