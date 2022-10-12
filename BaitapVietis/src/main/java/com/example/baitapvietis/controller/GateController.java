package com.example.baitapvietis.controller;

import com.example.baitapvietis.exception.NotFoundException;
import com.example.baitapvietis.model.entity.GatesEntity;
import com.example.baitapvietis.service.GateService;
import com.example.baitapvietis.service.WasehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/gates")
public class GateController {

//    private final Gateservice gateService;
//    private final WasehouseService wasehouseService;
//
//    @Autowired
//    public GateController(
//            GateServiceImpl gateService,
//            WasehouseService wasehouseService
//    ){
//        this.gateService = gateService;
//        this.wasehouseService = wasehouseService;
//    }

    @Autowired
    private GateService gateService;

    @Autowired
    private  WasehouseService wasehouseService;


    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/list")
    public String getAll(Model model){
        model.addAttribute("gate", new GatesEntity());
        model.addAttribute("listWasehouse", wasehouseService.getAll());
        model.addAttribute("listGate", gateService.getAll());
        return "gate/list-gate";
    }

    @GetMapping("/add")
    public String getFormAdd(Model model){
        model.addAttribute("listWasehouse", wasehouseService.getAll());
        model.addAttribute("gate", new GatesEntity());

        return "gate/add-gate";
    }

    @GetMapping("/edit/{id}")
    private String getFormEdit(
            @PathVariable("id") Long id,
            Model model){
        Optional<GatesEntity> findById = Optional.ofNullable(gateService.findById(id).
                orElseThrow(() -> new NotFoundException("Gate id not found" + id)));
        GatesEntity gate = findById.get();

        model.addAttribute("gate", gate);
        model.addAttribute("gate",new GatesEntity());
        return "gate/edit-gate";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    private String create(
            @ModelAttribute("gate") GatesEntity gateEntity)
    {
        gateService.create(gateEntity);
        return "redirect:gates/list";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(
            @PathVariable("id") Long id,
            @ModelAttribute("gate") GatesEntity gateEntity
    ){
        gateService.update(id,gateEntity);
        return "redirect:gates/list";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    private String delete(@PathVariable("id") Long id){
        gateService.delete(id);
        return "redirect:gates/list";
    }


}
