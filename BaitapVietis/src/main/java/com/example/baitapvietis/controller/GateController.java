package com.example.baitapvietis.controller;

import com.example.baitapvietis.model.entity.GateEntity;
import com.example.baitapvietis.service.GateService;
import com.example.baitapvietis.service.WasehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/gates")
public class GateController {

    private final GateService gateService;
    private final WasehouseService wasehouseService;

    public GateController(
            GateService gateService,
            WasehouseService wasehouseService
    ){
        this.gateService = gateService;
        this.wasehouseService = wasehouseService;
    }


    @GetMapping("/list")
    public String getAll(Model model){
        model.addAttribute("gate", new GateEntity());
        model.addAttribute("listWasehouse", wasehouseService.getAll());
        model.addAttribute("listGate", gateService.getAll());
        return "gate/gate";
    }

    @PostMapping("/create")
    private String create(@ModelAttribute("gate")GateEntity gateEntity){

        gateService.create(gateEntity);
        return "redirect:gates/list";
    }

    @DeleteMapping("/delete/{id}")
    private String delete(@PathVariable("id") Long id){
        gateService.delete(id);
        return "redirect:gates/list";
    }


}
