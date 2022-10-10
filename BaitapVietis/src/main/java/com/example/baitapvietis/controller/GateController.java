package com.example.baitapvietis.controller;

import com.example.baitapvietis.model.entity.GateEntity;
import com.example.baitapvietis.service.Gateservice;
import com.example.baitapvietis.service.WasehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/gates")
public class GateController {

    private final Gateservice gateService;
    private final WasehouseService wasehouseService;

    @Autowired
    public GateController(
            Gateservice gateService,
            WasehouseService wasehouseService
    ){
        this.gateService = gateService;
        this.wasehouseService = wasehouseService;
    }


    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/list")
    public String getAll(Model model){
        model.addAttribute("gate", new GateEntity());
        model.addAttribute("listWasehouse", wasehouseService.getAll());
        model.addAttribute("listGate", gateService.getAll());
        return "gate/list-gate";
    }

    @GetMapping("/add")
    public String getFormAdd(Model model){
        model.addAttribute("listWasehouse", wasehouseService.getAll());
        model.addAttribute("gate", new GateEntity());

        return "gate/add-gate";
    }

    @GetMapping("/edit/{id}")
    private String getFormEdit(Model model){
        model.addAttribute("gate",new GateEntity());
        return "gate/edit-gate";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    private String create(
            @ModelAttribute("gate") GateEntity gateEntity)
    {
        gateService.create(gateEntity);
        return "redirect:gates/list";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    private String delete(@PathVariable("id") Long id){
        gateService.delete(id);
        return "redirect:gates/list";
    }


}
