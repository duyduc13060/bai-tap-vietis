package com.example.baitapvietis.controller;

import com.example.baitapvietis.exception.NotFoundException;
import com.example.baitapvietis.model.entity.ShelfEntity;
import com.example.baitapvietis.service.ShelfService;
import com.example.baitapvietis.service.WasehouseService;
import com.example.baitapvietis.service.impl.ShelfServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/shelfs")
public class ShelfController {

    private final ShelfService shelfService;
    private final WasehouseService wasehouseService;

    public ShelfController(
            ShelfService shelfService,
            WasehouseService wasehouseService

    ){
        this.shelfService = shelfService;
        this.wasehouseService = wasehouseService;
    }


//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/list")
    public String getAll(Model model){
        model.addAttribute("listWasehouse", wasehouseService.getAll());
        model.addAttribute("listshelf",shelfService.getALl());
        return "shelf/list-shelf";
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add")
    public String loadForm(Model model){
        model.addAttribute("shelf",new ShelfEntity());
        model.addAttribute("listWasehouse", wasehouseService.getAll());
        return "shelf/add-shelf";
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit/{id}")
    public String loadFormEdit(@PathVariable("id") Long id, Model model){
        Optional<ShelfEntity> findById = Optional.ofNullable(shelfService.findById(id).
                orElseThrow(() -> new NotFoundException("Id notfound" + id)));

        ShelfEntity shelfEntity = findById.get();
        model.addAttribute("shelf",shelfEntity);

        return "shelf/edit-shelf";
    }


//    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(
            @ModelAttribute("shelf") ShelfEntity shelfEntity
    ){
        shelfService.create(shelfEntity);
        return "redirect:/shelfs/list";
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(
            @PathVariable("id") Long id,
            @ModelAttribute("shelf") ShelfEntity shelfEntity
    ){
        shelfService.update(id, shelfEntity);
        return "redirect:/shelfs/list";
    }




}
