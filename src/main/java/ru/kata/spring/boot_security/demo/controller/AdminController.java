package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.RoleService;
import java.security.Principal;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String index(@ModelAttribute("user") User user, Principal principal, Model model) {
        model.addAttribute("myUser", userService.findByFirstname(principal.getName()));
        model.addAttribute("roles", roleService.getRolesSet());
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @PostMapping("/")
    public String save(@ModelAttribute("user") User user, @RequestParam("roleIds") Set<Long> roleIds) {
        userService.setUserRoles(user, roleIds);
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @RequestParam("roleIds") Set<Long> roleIds) {
        userService.setUserRoles(user, roleIds);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
