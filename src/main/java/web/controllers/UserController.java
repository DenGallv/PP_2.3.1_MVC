package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.services.UserService;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getAllUsers(Model model) {
        model.addAttribute("userList", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/info")
    public String addUser(Model model) {
        model.addAttribute("newUser", new User());
        return "user-info";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("newUser") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("userFromDB", userService.getUser(id));
        return "user-edit";
    }

    @PatchMapping("/updateUser")
    public String updateUser(@ModelAttribute("userFromDB") User user) {
        userService.updateUser(user);
        return "redirect:/";
    }

    @DeleteMapping("/deleteUser")
    public String delete(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

}
