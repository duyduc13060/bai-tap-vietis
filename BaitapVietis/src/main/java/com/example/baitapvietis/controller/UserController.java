package com.example.baitapvietis.controller;

import com.example.baitapvietis.contants.RoleEnum;
import com.example.baitapvietis.exception.NotFoundException;
import com.example.baitapvietis.model.entity.UserEntity;
import com.example.baitapvietis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/list")
    public String listUser(Model model){
        model.addAttribute("listUser", userService.listUser());
        return "user/list-user";
    }

    @GetMapping("/add")
    public String loadForm(
            Model model
    ){
        RoleEnum[] role = RoleEnum.values();
        model.addAttribute("roles", role);
        model.addAttribute("user",new UserEntity());

        return "user/add-user";
    }

    @GetMapping("/edit/{id}")
    public String editUser(
            Model model,
            @PathVariable("id") Long id
    ){
        Optional<UserEntity> findById = Optional.ofNullable(userService.findById(id).
                orElseThrow(() -> new NotFoundException("Id not found" + id)));

        UserEntity user = findById.get();
        model.addAttribute("user", user);

        return "user/edit-user";
    }



    @PostMapping("/create")
    public String create(
            @ModelAttribute("user")UserEntity userEntity
    ){
        userService.create(userEntity);

        return "redirect:/users/list";
    }



}
