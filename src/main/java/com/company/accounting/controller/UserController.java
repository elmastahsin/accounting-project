package com.company.accounting.controller;

import com.company.accounting.dto.UserDTO;
import com.company.accounting.service.CompanyService;
import com.company.accounting.service.RoleService;
import com.company.accounting.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final CompanyService companyService;

    public UserController(UserService userService, RoleService roleService, CompanyService companyService) {
        this.userService = userService;
        this.roleService = roleService;
        this.companyService = companyService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/user-list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("newUser", new UserDTO());
        model.addAttribute("userRoles", roleService.getRolesForCurrentUser());
        model.addAttribute("companies", companyService.getCompaniesForCurrentUser());
        return "user/user-create";
    }

    //create PostMapping
    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("newUser") UserDTO userDTO, BindingResult bindingResult) {

        if (userService.isExist(userDTO)) {
            bindingResult.rejectValue("title", " ", "This title already exists.");
        }

        if (bindingResult.hasErrors()) {
            return "user/user-create";
        }

        userService.save(userDTO);

        return "redirect:/user/list";


    }

    //update
    @GetMapping("/update/{userId}")
    public String update(@PathVariable Long userId, Model model){
        model.addAttribute("user", userService.findById(userId));
        model.addAttribute("userRoles", roleService.getRolesForCurrentUser());
        model.addAttribute("companies", companyService.getCompaniesForCurrentUser());
        return "user/user-update";
    }

    //update PostMapping
    @PostMapping
    public String update(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult, @PathVariable Long userId, Model model) throws CloneNotSupportedException {
        if (bindingResult.hasErrors()) {
            return "user/user-update" + userId;
        }
        userService.update(userDTO, userId);

        return "redirect:/user/list";
    }

//delete Get Mapping
    @GetMapping("/delete/{userId}")
    public String delete(@PathVariable Long userId) {
        userService.delete(userId);
        return "redirect:/user/list";
    }



}

