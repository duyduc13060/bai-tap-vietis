package com.example.baitapvietis.controller;

import com.example.baitapvietis.exception.NotFoundException;
import com.example.baitapvietis.model.entity.WasehouseEntity;
import com.example.baitapvietis.service.WasehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    //@PreAuthorize("hasAnyRole('USER','ADMIN')")
    public String listWasehouse(Model model){
        model.addAttribute("listWasehouse", wasehouseService.getAll());
        return "wasehouse/list-wasehouse";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
   // @PreAuthorize("hasRole('ADMIN')")
    public String loadform(Model model){
        model.addAttribute("wasehouse",new WasehouseEntity());
        return "wasehouse/add-wasehouse";
    }

   // @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String loadFormEdit(@PathVariable("id") Long id,Model model){

        Optional<WasehouseEntity> findById = Optional.ofNullable(wasehouseService.findById(id).
                orElseThrow(() -> new NotFoundException("Wasehouse Id not found")));

        WasehouseEntity wasehouse = findById.get();
        model.addAttribute("wasehouse", wasehouse);

        return "wasehouse/edit-wasehouse";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String create(
            @ModelAttribute("wasehouse") WasehouseEntity wasehouseEntity,
            Model model){
        wasehouseService.createWasehouse(wasehouseEntity);
        return "redirect:/wasehouses/list";
    }

  //  @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute("wasehouse") WasehouseEntity wasehouseEntity){
        wasehouseService.updateWasehouse(id,wasehouseEntity);
        return "redirect:/wasehouses/list";
    }


  //  @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id){
        wasehouseService.deleteWasehouse(id);
        System.out.println(id + "444");
        return "redirect:/wasehouses/list";
    }



}
