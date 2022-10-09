package com.example.baitapvietis.controller;

import com.example.baitapvietis.payload.request.ChangePassword;
import com.example.baitapvietis.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private final AuthService authService;

    public AuthController(
            AuthService authService
    ){
        this.authService = authService;
    }

    @RequestMapping(value = "/auth/login/form",method = RequestMethod.GET)
    public String form(){
        return "security/login";
    }

    @RequestMapping(value = "/auth/login/success",method = RequestMethod.GET)
    public String success(Model model){
        model.addAttribute("message","dang nhap thanh cong");
        return "security/login";
    }

    @RequestMapping(value = "/auth/login/error",method = RequestMethod.GET)
    public String error(Model model){
        model.addAttribute("message","Dang nhap that bai");
        return "security/login";
    }

    @RequestMapping(value = "/auth/change", method = RequestMethod.GET)
    public String getForm(Model model){
        model.addAttribute("change", new ChangePassword());
        return "user/change-password";
    }

    @RequestMapping(value = "/auth/access/denied",method = RequestMethod.GET)
    public String accessDenied(Model model){
        model.addAttribute("message","Ban khong co quyen truy xuat");
        return "security/login";
    }

    @RequestMapping(value = "/auth/logoff/success",method = RequestMethod.GET)
    public String logoff(Model model){
        model.addAttribute("message","Dang xuat thanh cong");
        return "security/login";
    }


    @RequestMapping(value = "/auth/change-pasword",method = RequestMethod.POST)
    public String changePassword(
            @ModelAttribute("changePassword")ChangePassword changePassword
            ){
        authService.changePassword(changePassword);
        return "redirect:/home/index";
    }






}
