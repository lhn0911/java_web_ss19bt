package com.data.java_ss19.controller;

import com.data.java_ss19.entity.User;
import com.data.java_ss19.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String listUsers(Model model, @RequestParam(defaultValue = "1") int page) {
        int pageSize = 5;
        List<User> users = userService.getUsers(page, pageSize);
        long totalUsers = userService.countUsers();
        int totalPages = (int) Math.ceil((double) totalUsers / pageSize);

        model.addAttribute("users", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

//        for (int i = 1; i <= 10; i++) {
//            User user = new User();
//            user.setUsername("user" + i);
//            user.setPassword("pass" + i);
//            user.setEmail("user" + i + "@example.com");
//            user.setPhoneNumber("090000000" + i);
//            user.setActive(true);
//            userService.saveUser(user);
//        }

        return "listUser";
    }

    @PostMapping("/status")
    public String changeStatus(@RequestParam Long userId, @RequestParam boolean isActive) {
        userService.updateUserStatus(userId, isActive);
        return "redirect:/users";
    }

}

