package com.example.demojpamultidatasource.api;

import com.example.demojpamultidatasource.business.UserService;
import com.example.demojpamultidatasource.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("")
    public UserDto postUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @GetMapping("/{membership}/{email}")
    public UserDto getUser(@PathVariable String membership, @PathVariable(name = "email") String userEmail) {
        return userService.getUser(userEmail, membership);
    }
}
