package com.example.baitapvietis.controller;

import com.example.baitapvietis.model.dto.StatusClassficationDto;
import com.example.baitapvietis.model.entity.StatusClassficationEntity;
import com.example.baitapvietis.model.entity.StatusEntity;
import com.example.baitapvietis.service.StatusClassificationService;
import com.example.baitapvietis.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private StatusService service;

    @Autowired
    private StatusClassificationService classificationService;


    @GetMapping("/list")
    public String findAll(Model model){



        List<StatusClassficationDto> list = service.findAll();
        model.addAttribute("listStatus",list);



        return "status/list-status";
    }

    @GetMapping("/add")
    public String getFormAdd(Model model){

        List<StatusClassficationEntity> listClassification = classificationService.findAll();
        model.addAttribute("listclassifi",listClassification);

        model.addAttribute("status",new StatusEntity());
        return "status/add-status";
    }

    @PostMapping("/create")
    public String create(
            @ModelAttribute("status") StatusEntity statusEntity
    ){
        service.create(statusEntity);
        return "redirect:/status/list";
    }

    @GetMapping("/edit/{id}")
    public String getFormEdit(
            @PathVariable("id") Long id,
            Model model
    ){
        List<StatusClassficationEntity> listClassification = classificationService.findAll();
        model.addAttribute("listclassifi",listClassification);

        StatusEntity findById = service.findById(id);
        model.addAttribute("status",findById);

        return "status/edit-status";
    }

    @PostMapping("/update/{id}")
    public String update(
            @PathVariable("id") Long id,
            @ModelAttribute("status") StatusEntity statusEntity
    ){
        service.update(id,statusEntity);
        return "redirect:/status/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable("id") Long id
    ){
        service.delete(id);
        return "redirect:/status/list";
    }



}
