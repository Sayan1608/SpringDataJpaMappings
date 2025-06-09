package com.mapping.controllers;

import com.mapping.entities.MovieSeat;
import com.mapping.entities.UserEntity;
import com.mapping.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserEntity createNewUser(@RequestBody UserEntity userEntity){
        return userService.createNewUser(userEntity);
    }

    @GetMapping(path = "/{userId}")
    public UserEntity getUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }

}
