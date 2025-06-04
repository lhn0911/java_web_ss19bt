package com.data.java_ss19.controller;

import com.data.java_ss19.entity.ScreenRoom;
import com.data.java_ss19.entity.Theater;
import com.data.java_ss19.service.ScreenRoomService;
import com.data.java_ss19.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/screenrooms")
public class ScreenRoomController {
    @Autowired
    private TheaterService theaterService;
    @Autowired
    private ScreenRoomService screenRoomService;
    @GetMapping
    public String list(Model model) {
        model.addAttribute("rooms", screenRoomService.findAllActive());
        return "list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("screenRoom", new ScreenRoom());
        return "add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute ScreenRoom screenRoom, BindingResult result, @RequestParam Long theaterId) {
        if (result.hasErrors()) return "/add";
        Theater theater = theaterService.findById(theaterId);
        screenRoom.setTheater(theater);
//        screenRoom.setSeats(generateSeats(screenRoom, screenRoom.getCapacity()));
        screenRoomService.save(screenRoom);
        return "redirect:/screenrooms";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam Long id, Model model) {
        model.addAttribute("screenRoom", screenRoomService.findById(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute ScreenRoom screenRoom, BindingResult result) {
        if (result.hasErrors()) return "edit";
        screenRoomService.update(screenRoom);
        return "redirect:/screenrooms";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id) {
        screenRoomService.deactivate(id);
        return "redirect:/screenrooms";
    }

}
