package com.data.java_ss19.controller;

import com.data.java_ss19.dto.TheaterDTO;
import com.data.java_ss19.entity.Theater;
import com.data.java_ss19.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/theaters")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    private TheaterDTO convertToDTO(Theater theater) {
        TheaterDTO dto = new TheaterDTO();
        dto.setId(theater.getId());
        dto.setTheaterName(theater.getTheaterName());
        dto.setAddress(theater.getAddress());
        dto.setNumberScreenRoom(theater.getNumberScreenRoom());
        dto.setStatus(theater.isStatus());
        return dto;
    }

    private Theater convertToEntity(TheaterDTO dto) {
        Theater theater = new Theater();
        theater.setId(dto.getId());
        theater.setTheaterName(dto.getTheaterName());
        theater.setAddress(dto.getAddress());
        theater.setNumberScreenRoom(dto.getNumberScreenRoom());
        theater.setStatus(dto.isStatus());
        return theater;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("theaters", theaterService.findAll());
        return "listTheater";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("theaterDTO", new TheaterDTO());
        return "addTheater";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("theaterDTO") TheaterDTO theaterDTO,
                      BindingResult result) {
        if (result.hasErrors()) {
            return "addTheater";
        }
        Theater theater = convertToEntity(theaterDTO);
        theaterService.save(theater);
        return "redirect:/theaters";
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam Long id, Model model) {
        Theater theater = theaterService.findById(id);
        if (theater == null) {
            return "redirect:/theaters";
        }
        model.addAttribute("theaterDTO", convertToDTO(theater));
        return "editTheater";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute("theaterDTO") TheaterDTO theaterDTO,
                       BindingResult result) {
        if (result.hasErrors()) {
            return "editTheater";
        }
        Theater theater = convertToEntity(theaterDTO);
        theaterService.update(theater);
        return "redirect:/theaters";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        if (theaterService.hasSchedule(id)) {
            Theater theater = theaterService.findById(id);
            if (theater != null) {
                theater.setStatus(false);
                theaterService.update(theater);
                redirectAttributes.addFlashAttribute("message", "Không thể xóa do có lịch chiếu, đã chuyển trạng thái ngừng hoạt động.");
            }
        } else {
            theaterService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Xóa rạp thành công.");
        }
        return "redirect:/theaters";
    }
}
