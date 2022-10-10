package com.example.baitapvietis.controller;

import com.example.baitapvietis.contants.RoleEnum;
import com.example.baitapvietis.exception.NotFoundException;
import com.example.baitapvietis.model.entity.UserEntity;
import com.example.baitapvietis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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


    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/list")
    public String listUser(Model model){
        model.addAttribute("listUser", userService.listUser());
        return "user/list-user";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping( "/add")
    public String loadForm(
            Model model
    ){
        RoleEnum[] role = RoleEnum.values();
        model.addAttribute("roles", role);
        model.addAttribute("user",new UserEntity());

        return "user/add-user";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{id}")
    public String editUser(
            Model model,
            @PathVariable("id") Long id
    ){
        Optional<UserEntity> findById = Optional.ofNullable(userService.findById(id).
                orElseThrow(() -> new NotFoundException("Id not found" + id)));

        UserEntity user = findById.get();
        RoleEnum [] roleEnums = RoleEnum.values();

        model.addAttribute("role", roleEnums);
        model.addAttribute("user", user);

        return "user/edit-user";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String create(
            @ModelAttribute("user")UserEntity userEntity
    ){
        userService.create(userEntity);

        return "redirect:/users/list";
    }

    @RequestMapping(value = "/update/{id}")
    public String update(
            @PathVariable("id") Long id,
            @ModelAttribute("user") UserEntity userEntity

    ){
        userService.update(id,userEntity);
        return "redirect:/users/list";
    }


    @RequestMapping("/search")
    public String search(
            @RequestParam(name = "username") String username,
            Model model
    ){
        List<UserEntity> userEntityList = userService.search(username);
        model.addAttribute("listUser",userEntityList);
        return "user/list-user";
    }



}
