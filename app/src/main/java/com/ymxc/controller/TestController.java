package com.ymxc.controller;

import com.ymxc.webmvc.Restful;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public Restful test(){
        return Restful.SUCCESS().object("hello").build();
    }

}
