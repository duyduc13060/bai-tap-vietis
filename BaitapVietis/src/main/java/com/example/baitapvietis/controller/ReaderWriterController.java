package com.example.baitapvietis.controller;

import com.example.baitapvietis.exception.NotFoundException;
import com.example.baitapvietis.model.entity.ReaderWriterEntity;
import com.example.baitapvietis.service.ReaderWriterService;
import com.example.baitapvietis.service.WasehouseService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
@RequestMapping("/readers")
public class ReaderWriterController {

    private final ReaderWriterService readerWriterService;
    private final WasehouseService wasehouseService;

    public ReaderWriterController(
            ReaderWriterService readerWriterService,
            WasehouseService wasehouseService
    ){
        this.readerWriterService = readerWriterService;
        this.wasehouseService = wasehouseService;
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @RequestMapping("/list")
    public String getAll(Model model){
        model.addAttribute("listReader",readerWriterService.getAll());
        return "readerWriter/list-readerWriter";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping("/add")
    public String getFormAdd(Model model){
        model.addAttribute("reader", new ReaderWriterEntity());
        model.addAttribute("listWasehouse",wasehouseService.getAll());
        return "readerWriter/add-readerWriter";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping("/edit/{id}")
    public String getFormEdit(@PathVariable("id") Long id, Model model){
        Optional<ReaderWriterEntity> readerWriter = Optional.ofNullable(readerWriterService.findById(id).
                orElseThrow(() -> new NotFoundException("Reader id not found" + id)));
        model.addAttribute("reader",readerWriter.get());
        model.addAttribute("listWasehouse",wasehouseService.getAll());
        return "readerWriter/edit-readerWriter";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(
            @ModelAttribute("reader") ReaderWriterEntity readerWriterEntity
    ){
        readerWriterService.create(readerWriterEntity);
        return "redirect:/readers/list";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(
            @PathVariable("id") Long id,
            @ModelAttribute("reader") ReaderWriterEntity readerWriterEntity
    ){
        readerWriterService.update(id,readerWriterEntity);
        return "redirect:/readers/list";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.GET)
    public String delete(
            @PathVariable("id") Long id
    ){
        readerWriterService.delete(id);
        return "redirect:/readers/list";
    }





}
