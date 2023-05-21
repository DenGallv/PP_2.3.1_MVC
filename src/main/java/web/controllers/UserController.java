package web.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.models.User;
import web.services.UserService;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getAllUsers(Model model) {
        model.addAttribute("userList", userService.getAllUsers());
        return "users";
    }

    @RequestMapping("/info")
    public String addUser(Model model) {
        model.addAttribute("newUser", new User());
        return "user-info";
    }

    @RequestMapping("/saveUser")
    public String saveUser(@ModelAttribute("newUser") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @RequestMapping("/edit")
    public String editUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("userFromDB", userService.getUser(id));
        return "user-edit";
    }

    @RequestMapping("/updateUser")
    public String updateUser(@ModelAttribute("userFromDB") User user, @RequestParam("id") Long id) {
        userService.updateUser(user, id);
        return "redirect:/";
    }

    @RequestMapping("/deleteUser")
    public String delete(@RequestParam("id")Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

}
