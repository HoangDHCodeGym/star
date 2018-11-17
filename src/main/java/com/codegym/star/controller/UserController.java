package com.codegym.star.controller;

import com.codegym.star.model.Star;
import com.codegym.star.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController {
    private List<String> starTypes = Arrays.asList("O", "B", "A", "F", "G", "K", "M");
    @Autowired
    StarService starService;
    @GetMapping("/")
    public String index(Model model, @RequestParam("query") Optional<String> query) {
        if (query.isPresent()) {
            String queryText = query.get();
            Iterable<Star> stars = starService.findByCodeOrName(queryText);
            model.addAttribute("stars", stars);
            return "index";
        }
        Iterable<Star> stars = starService.findAll();
        model.addAttribute("stars", stars);
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Star star = new Star();
        model.addAttribute("star", star);
        model.addAttribute("starTypes", starTypes);
        return "create";
    }

    @PostMapping("/create")
    public String save(Model model, @Valid @ModelAttribute Star star, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create";
        }
        starService.save(star);
        model.addAttribute("message", "New star created successfully");
        return "create";
    }

    @GetMapping("/edit/{code}")
    public String edit(Model model, @PathVariable("code") String code) {
        model.addAttribute("starTypes", starTypes);
        model.addAttribute("star",starService.findByCode(code));
        return "edit";
    }

    @PostMapping("/edit")
    public String saveEdited(Model model, @Valid @ModelAttribute Star star, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        starService.save(star);
        model.addAttribute("message", "Edited Successfully");
        return "edit";
    }

    @GetMapping("/delete/{code}")
    public String delete(Model model, @PathVariable("code") String code, RedirectAttributes redirect) {
        starService.deleteByCode(code);
        redirect.addAttribute("message", "Deleted Successfully");
        return "redirect:/";
    }

    @PostMapping("/search")
    public String search(Model model, @ModelAttribute("searchText") String query, RedirectAttributes redirect) {
        redirect.addAttribute("query", query);
        return "redirect:/";
    }
}
