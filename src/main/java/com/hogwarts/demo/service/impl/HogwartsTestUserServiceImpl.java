package com.hogwarts.demo.service.impl;

import com.hogwarts.demo.dto.UserDto;
import com.hogwarts.demo.service.HogwartsTestUserService;
import org.springframework.stereotype.Component;


@Component
public class HogwartsTestUserServiceImpl implements HogwartsTestUserService {


    @Override
    public String login(UserDto userDto) {

        System.out.println("name: " + userDto.getName());
        System.out.println("pwd: " + userDto.getPwd());

        return userDto.getName() + "-" + userDto.getPwd();
    }
}
