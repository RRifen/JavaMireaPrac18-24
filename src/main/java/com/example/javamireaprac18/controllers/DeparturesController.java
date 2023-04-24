package com.example.javamireaprac18.controllers;

import com.example.javamireaprac18.services.DeparturesService;
import com.example.javamireaprac18.models.Departure;
import com.example.javamireaprac18.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@Controller
@RequestMapping("/departures")
public class DeparturesController {

    private final DeparturesService departuresService;
    private final EmailService emailService;

    @Autowired
    public DeparturesController(DeparturesService departuresService, EmailService emailService) {
        this.departuresService = departuresService;
        this.emailService = emailService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("departures", departuresService.finaAll());
        return "departures/index";
    }

    @GetMapping("/type")
    public String indexTypeFilter(@RequestParam("type") String type,
                                  Model model) {
        model.addAttribute("departures", departuresService.findAllByType(type));
        return "departures/index";
    }

    @GetMapping("/date")
    public String indexDateFilter(@RequestParam("date") String date,
                                  Model model) {
        model.addAttribute("departures", departuresService.findAllByDate(date));
        return "departures/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("departure", departuresService.findOne(id));
        return "departures/show";
    }

    @GetMapping("/new")
    public String newDeparture(@ModelAttribute("departure") Departure departure) {
        return "departures/new";
    }

    @PostMapping
    public String create(@ModelAttribute("departure") Departure departure) {
        departuresService.save(departure);
        emailService.sendEmailMessageWithNewDeparture(departure);
        return "redirect:/departures";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("departure", departuresService.findOne(id));
        return "departures/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("departure") Departure departure,
                         @PathVariable("id") int id) {
        departuresService.update(id, departure);
        return "redirect:/departures";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        departuresService.delete(id);
        return "redirect:/departures";
    }
}
