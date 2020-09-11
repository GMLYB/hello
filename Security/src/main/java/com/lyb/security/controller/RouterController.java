package com.lyb.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class RouterController {

    @RequestMapping({"/index", "/"})
    public String index() {
        return "index";
    }


    @RequestMapping("/hello")
    public String login(){
        return "views/login";
    }

    @RequestMapping("/level1/{id}")
    public String toPage1(@PathVariable("id") int id) {
        return "views/level1/" + id;
    }

    @RequestMapping("/level2/{id}")
    public String toPage2(@PathVariable("id") int id) {
        return "views/level2/" + id;
    }

    @RequestMapping("/level3/{id}")
    public String toPage3(@PathVariable("id") int id) {
        return "views/level3/" + id;
    }



    public String toPage4(Map<String,Object> map) {

        for (Map.Entry<String,Object> entry : map.entrySet()){
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }

       return "";
    }

}
