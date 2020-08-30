package com.lyb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.lyb.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
//@RestController
public class GsonController {

    @RequestMapping(value = "/j1",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String json1(){
        User user = new User(1,"白了少年头",18);
        Gson gson = new Gson();
        String json = gson.toJson(user);
        return json;
    }

//    @RequestMapping(value = "/j2",produces = "application/json;charset=utf-8")
    @RequestMapping(value = "/j2")
    @ResponseBody
    public String json2(){
        List<User> list = new ArrayList<>();
        User user1 = new User(1,"白了少年头1",18);
        User user2 = new User(1,"白了少年头2",18);
        User user3 = new User(1,"白了少年头3",18);
        User user4 = new User(1,"白了少年头4",18);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @RequestMapping(value = "/j3",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String json3() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Date date = new Date();
        String json = mapper.writeValueAsString(date);
        return json;
    }
}
