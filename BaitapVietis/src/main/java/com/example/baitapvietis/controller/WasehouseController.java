package com.example.baitapvietis.controller;

import com.example.baitapvietis.exception.NotFoundException;
import com.example.baitapvietis.model.entity.WasehouseEntity;
import com.example.baitapvietis.service.WasehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/wasehouses")
public class WasehouseController {

    @Autowired
    private WasehouseService wasehouseService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String listWasehouse(Model model){
        model.addAttribute("listWasehouse", wasehouseService.getAll());
        return "wasehouse/list-wasehouse";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String loadform(Model model){
        model.addAttribute("wasehouse",new WasehouseEntity());
        return "wasehouse/add-wasehouse";
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String loadFormEdit(@PathVariable("id") Long id,Model model){

        Optional<WasehouseEntity> findById = Optional.ofNullable(wasehouseService.findById(id).
                orElseThrow(() -> new NotFoundException("Wasehouse Id not found")));

        WasehouseEntity wasehouse = findById.get();
        model.addAttribute("wasehouse", wasehouse);

        return "wasehouse/edit-wasehouse";
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String create(
            @ModelAttribute("wasehouse") WasehouseEntity wasehouseEntity,
            Model model){
        wasehouseService.createWasehouse(wasehouseEntity);
        return "redirect:/wasehouses/list";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute("wasehouse") WasehouseEntity wasehouseEntity){
        wasehouseService.updateWasehouse(id,wasehouseEntity);
        return "redirect:/wasehouses/list";
    }


    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id){
        wasehouseService.deleteWasehouse(id);
        System.out.println(id + "444");
        return "redirect:/wasehouses/list";
    }



}
