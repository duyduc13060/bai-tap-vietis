package com.example.baitapvietis.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@RequestMapping("/home")
public class HomeController {

   // @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/home/index", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("message","ban phai dang nhap");
        return "redirect:/users/list";
    }

}
