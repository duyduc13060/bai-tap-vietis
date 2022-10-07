package com.example.baitapvietis.controller;

import com.example.baitapvietis.payload.request.ChangePassword;
import com.example.baitapvietis.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(
            AuthService authService
    ){
        this.authService = authService;
    }

    @RequestMapping(value = "/login/form",method = RequestMethod.GET)
    public String form(){
        return "security/login";
    }

    @RequestMapping(value = "/login/success",method = RequestMethod.GET)
    public String success(Model model){
        model.addAttribute("message","dang nhap thanh cong");
        return "redirect:/home/index";
    }

    @RequestMapping(value = "/login/error",method = RequestMethod.GET)
    public String error(Model model){
        model.addAttribute("message","Dang nhap that bai");
        return "redirect:/auth/login/form";
    }

    @RequestMapping(value = "/change", method = RequestMethod.GET)
    public String getForm(Model model){
        model.addAttribute("change", new ChangePassword());
        return "user/change-password";
    }


    @RequestMapping(value = "/change-pasword",method = RequestMethod.POST)
    public String changePassword(
            @ModelAttribute("changePassword")ChangePassword changePassword
            ){
        authService.changePassword(changePassword);
        return "redirect:/home/index";
    }






}
