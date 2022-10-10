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


    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/list")
    public String getAll(Model model){
        model.addAttribute("listWasehouse", wasehouseService.getAll());
        model.addAttribute("listShelf",shelfService.getALl());
        return "shelf/list-shelf";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add")
    public String loadForm(Model model){
        model.addAttribute("shelf",new ShelfEntity());
        model.addAttribute("listWasehouse", wasehouseService.getAll());
        return "shelf/add-shelf";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{id}")
    public String loadFormEdit(@PathVariable("id") Long id, Model model){
        Optional<ShelfEntity> findById = Optional.ofNullable(shelfService.findById(id).
                orElseThrow(() -> new NotFoundException("Id notfound" + id)));
        ShelfEntity shelfEntity = findById.get();

        model.addAttribute("shelf",shelfEntity);
        model.addAttribute("listWasehouse", wasehouseService.getAll());

        return "shelf/edit-shelf";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(
            @ModelAttribute("shelf") ShelfEntity shelfEntity
    ){
        shelfService.create(shelfEntity);
        return "redirect:/shelfs/list";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(
            @PathVariable("id") Long id,
            @ModelAttribute("shelf") ShelfEntity shelfEntity
    ){
        shelfService.update(id, shelfEntity);
        return "redirect:/shelfs/list";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        shelfService.delete(id);
        return "redirect:/shelfs/list";
    }


    @RequestMapping("/search")
    public String search(
        @RequestParam(value = "id",required = false) Long id ,
        Model model
    ){
        model.addAttribute("listWasehouse", wasehouseService.getAll());

        List<ShelfEntity> shelfEntityList = shelfService.search(id);
        model.addAttribute("listShelf",shelfEntityList);

        return "shelf/list-shelf";
    }




}
