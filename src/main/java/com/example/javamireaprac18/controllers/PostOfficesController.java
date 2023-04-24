package com.example.javamireaprac18.controllers;

import com.example.javamireaprac18.services.EmailService;
import com.example.javamireaprac18.services.PostOfficesService;
import com.example.javamireaprac18.models.PostOffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/postoffices")
public class PostOfficesController {

    private final PostOfficesService postOfficesService;
    private final EmailService emailService;

    @Autowired
    public PostOfficesController(PostOfficesService postOfficesService, EmailService emailService) {
        this.postOfficesService = postOfficesService;
        this.emailService = emailService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("postoffices", postOfficesService.findAll());
        return "postoffices/index";
    }

    @GetMapping("/name")
    public String indexNameFilter(@RequestParam("name") String name,
                                  Model model) {
        model.addAttribute("postoffices", postOfficesService.findAllByName(name));
        return "postoffices/index";
    }

    @GetMapping("/city_name")
    public String indexCityNameFilter(@RequestParam("city_name") String cityName,
                                  Model model) {
        model.addAttribute("postoffices", postOfficesService.findAllByCityName(cityName));
        return "postoffices/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("postoffice", postOfficesService.findOne(id));
        return "postoffices/show";
    }

    @GetMapping("/new")
    public String newPostOffice(@ModelAttribute("postoffice") PostOffice postOffice) {
        return "postoffices/new";
    }

    @PostMapping
    public String create(@ModelAttribute("postoffice") PostOffice postOffice) {
        postOfficesService.save(postOffice);
        emailService.sendEmailMessageWithNewPostOffice(postOffice);
        return "redirect:/postoffices";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("postoffice", postOfficesService.findOne(id));
        return "postoffices/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("postoffice") PostOffice postOffice,
                         @PathVariable("id") int id) {
        postOfficesService.update(id, postOffice);
        return "redirect:/postoffices";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        postOfficesService.delete(id);
        return "redirect:/postoffices";
    }
}

