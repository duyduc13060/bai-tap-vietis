package com.example.baitapvietis.controller;

import com.example.baitapvietis.exception.NotFoundException;
import com.example.baitapvietis.model.entity.WarehouseEntity;
import com.example.baitapvietis.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    //@PreAuthorize("hasAnyRole('USER','ADMIN')")
    public String listWasehouse(Model model){
        model.addAttribute("listWarehouse", warehouseService.getAll());
        return "warehouse/list-warehouse";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/add",method = RequestMethod.GET)
   // @PreAuthorize("hasRole('ADMIN')")
    public String loadform(Model model){
        model.addAttribute("warehouse",new WarehouseEntity());
        return "warehouse/add-warehouse";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String loadFormEdit(@PathVariable("id") Long id,Model model){

        Optional<WarehouseEntity> findById = Optional.ofNullable(warehouseService.findById(id).
                orElseThrow(() -> new NotFoundException("warehouse Id not found")));

        WarehouseEntity wasehouse = findById.get();
        model.addAttribute("warehouse", wasehouse);

        return "warehouse/edit-warehouse";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String create(
            @ModelAttribute("warehouse") WarehouseEntity warehouseEntity,
            Model model){
        warehouseService.createWasehouse(warehouseEntity);
        return "redirect:/warehouses/list";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute("warehouse") WarehouseEntity warehouseEntity){
        warehouseService.updateWasehouse(id, warehouseEntity);
        return "redirect:/warehouses/list";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id){
        warehouseService.deleteWasehouse(id);
        System.out.println(id + "444");
        return "redirect:/warehouses/list";
    }



}
