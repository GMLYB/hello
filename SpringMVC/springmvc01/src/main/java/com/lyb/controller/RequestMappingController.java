package com.lyb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jsp")
public class RequestMappingController {

    @RequestMapping("/test")
    public String test(){
        return "/jsp/b";
    }
}
