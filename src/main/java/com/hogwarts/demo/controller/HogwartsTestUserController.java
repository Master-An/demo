package com.hogwarts.demo.controller;


import com.hogwarts.demo.dto.UserDto;
import com.hogwarts.demo.service.HogwartsTestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("testUser")
public class HogwartsTestUserController {

    @Autowired
    private HogwartsTestUserService hogwartsTestUserService;

    @Value("hogwarts.key1")
    private String hogwartsKey1;

    @PostMapping("login")
    public String login(@RequestBody UserDto userDto){
        String result = hogwartsTestUserService.login(userDto);
        return "成功" + result;
    }

    @RequestMapping(value = "byId/{userId}/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable("userId") Long userId, @PathVariable("id") Long id){
        System.out.println("userId: " + userId);
        System.out.println("id: " + id);
        return "成功 " + userId + " id:" + id;
    }

//    @RequestMapping(value = "byId", method = RequestMethod.GET)
    @GetMapping("byId")
    public String getById2(@RequestParam(value = "userId", required = false) Long userId, @RequestParam("id") Long id){
        System.out.println("userId: " + userId);
        System.out.println("id: " + id);
        return "成功 " + userId + " id:" + id;
    }



}
