package com.data.java_ss19.controller;

import com.data.java_ss19.entity.Movie;
import com.data.java_ss19.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public String listMovies(Model model) {
        model.addAttribute("movies", movieService.findAll());
        return "listMovie";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "addMovie";
    }

    @PostMapping("/add")
    public String addMovie(@Valid @ModelAttribute("movie") Movie movie, BindingResult result) {
        if (result.hasErrors()) return "addMovie";
        movieService.save(movie);
        return "redirect:/movies";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam Long id, Model model) {
        model.addAttribute("movie", movieService.findById(id));
        return "editMovie";
    }

    @PostMapping("/edit")
    public String editMovie(@Valid @ModelAttribute("movie") Movie movie, BindingResult result) {
        if (result.hasErrors()) return "editMovie";
        movieService.update(movie);
        return "redirect:/movies";
    }

    @GetMapping("/delete")
    public String deleteMovie(@RequestParam Long id) {
        movieService.delete(id);
        return "redirect:/movies";
    }
}
