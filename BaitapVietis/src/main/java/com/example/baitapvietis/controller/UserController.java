package com.example.baitapvietis.controller;

import com.example.baitapvietis.contants.RoleEnum;
import com.example.baitapvietis.exception.NotFoundException;
import com.example.baitapvietis.model.dto.UserDto;
import com.example.baitapvietis.model.entity.UserEntity;
import com.example.baitapvietis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


//    @PreAuthorize("hasAnyAuthority('USER',ADMIN)")
    @GetMapping("/list")
    public String page(
            @RequestParam(value = "page",defaultValue = "1",required = false) Integer pageNumber,
            @RequestParam(value = "size",defaultValue = "10",required = false) Integer pageSize,
            @RequestParam(name = "username",required = false) String username
            ,Model model,
            Pageable pageable
    ){

        if(!StringUtils.hasText(username)){
            model.addAttribute("listUser",userService.pageNavigation(pageNumber,pageSize));
        }else{
            model.addAttribute("listUser",userService.serachAndPage(username, pageable));
        }

        return "user/list-user";
    }


//    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
//    @GetMapping("/list")
//    public String listUser(Model model) {
//        model.addAttribute("listUser", userService.listUser());
//        return "user/list-user";
//    }

   // @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add")
    public String loadForm(
            Model model
    ) {
        model.addAttribute("user", new UserDto());
        RoleEnum[] role = RoleEnum.values();
        model.addAttribute("roles", role);

        return "user/add-user";
    }

 //   @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{id}")
    public String editUser(
            Model model,
            @PathVariable("id") Long id
    ) {
        RoleEnum[] roleEnums = RoleEnum.values();
        model.addAttribute("role", roleEnums);

        UserDto user = userService.findById(id);
        model.addAttribute("user", user);

        return "user/edit-user";
    }

   // @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(
            @Valid @ModelAttribute("user") UserDto userDto,
            BindingResult bindingResult, Model model
    ) {
        RoleEnum[] role = RoleEnum.values();
        model.addAttribute("roles", role);

        if (bindingResult.hasErrors()) return "user/add-user";

        userService.create(userDto);

        return "redirect:/users/list";
    }

  //  @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(
            @PathVariable("id") Long id,
            @Valid @ModelAttribute("user") UserDto userDto,
            BindingResult bindingResult,
            Model model
    ) {
        RoleEnum[] role = RoleEnum.values();
        model.addAttribute("roles", role);

        if (bindingResult.hasErrors()) {
            return "user/edit-user";
        }else{
            userService.update(id, userDto);

        }

        return "redirect:/users/list";
    }

  //  @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users/list";
    }


//    @RequestMapping("/search")
//    public String search(
//            @RequestParam(name = "username",required = false) String username,
//            Model model
//    ) {
//        List<UserEntity> userEntityList = userService.search(username);
//        model.addAttribute("listUser", userEntityList);
//        return "user/list-user";
//    }


}
