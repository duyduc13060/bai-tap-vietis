package com.example.baitapvietis.controller;

import com.example.baitapvietis.service.ReaderWriterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/readers")
public class ReaderWriterController {

    private final ReaderWriterService readerWriterService;

    public ReaderWriterController(ReaderWriterService readerWriterService){
        this.readerWriterService = readerWriterService;
    }

    @RequestMapping("/list")
    public String getAll(Model model){
        model.addAttribute("listReader",readerWriterService.getAll());
        return "readerWriter/list-readerWriter";
    }





}
