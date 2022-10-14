package com.example.baitapvietis.controller;


import com.example.baitapvietis.model.dto.UserDto;
import com.example.baitapvietis.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class TestRestController {

    private final UserService userService;

    public TestRestController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid  @RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.create(userDto));
    }



}
