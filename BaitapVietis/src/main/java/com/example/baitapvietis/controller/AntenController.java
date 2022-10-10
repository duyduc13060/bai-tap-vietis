package com.example.baitapvietis.controller;

import com.example.baitapvietis.exception.NotFoundException;
import com.example.baitapvietis.model.entity.AntenEntity;
import com.example.baitapvietis.service.AntenService;
import com.example.baitapvietis.service.ReaderWriterService;
import com.example.baitapvietis.service.ShelfService;
import com.example.baitapvietis.service.WasehouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
@RequestMapping("/antens")
public class AntenController {

    private final AntenService antenService;
    private final WasehouseService wasehouseService;
    private final ShelfService shelfService;
    private final ReaderWriterService readerWriterService;

    public AntenController(
            AntenService antenService,
            WasehouseService wasehouseService,
            ShelfService shelfService,
            ReaderWriterService readerWriterService
    ){
        this.antenService = antenService;
        this.wasehouseService = wasehouseService;
        this.shelfService = shelfService;
        this.readerWriterService = readerWriterService;
    }

    @RequestMapping("/list")
    public String getAll(Model model){
        model.addAttribute("listAnten",antenService.getAll());
        model.addAttribute("listWasehouse", wasehouseService.getAll());
        return "anten/list-anten";
    }

    @RequestMapping("/add")
    public String getFormAdd(Model model){
        model.addAttribute("listReader", readerWriterService.getAll());
        model.addAttribute("listShelf", shelfService.getALl());
        model.addAttribute("anten", new AntenEntity());

        return "anten/add-anten";
    }

    @RequestMapping("/edit/{id}")
    public String getFormEdit(@PathVariable("id") Long id,  Model model){

        model.addAttribute("listReader", readerWriterService.getAll());
        model.addAttribute("listShelf", shelfService.getALl());

        Optional<AntenEntity> findById = Optional.ofNullable(antenService.findById(id).
                orElseThrow(() -> new NotFoundException("anten id not found " + id)));

        AntenEntity anten = findById.get();
        model.addAttribute("anten", anten);

        return "anten/edit-anten";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("anten") AntenEntity antenEntity){
        antenService.create(antenEntity);
        return "redirect:/antens/list";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(
            @PathVariable("id") Long id,
            @ModelAttribute("anten") AntenEntity antenEntity){
        antenService.update(id,antenEntity);
        return "redirect:/antens/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id){
        antenService.delete(id);
        return "redirect:/antens/list";
    }





}
