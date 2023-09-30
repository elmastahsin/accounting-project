package com.company.accounting.controller;

import com.company.accounting.converter.CategoryDtoConverter;
import com.company.accounting.dto.CategoryDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.company.accounting.service.CategoryService;

import javax.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("categories",categoryService.findAll());
        return "/category/category-list";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("newCategory", new CategoryDTO());
        return "/category/category-create";
    }
    @PostMapping("/create")
    public String create(@Valid @ModelAttribute ("newCategory") CategoryDTO categoryDTO, BindingResult bindingResult,Model model) {

        if (categoryService.isExist(categoryDTO)) {
            bindingResult.rejectValue("description", " ", "This category already exists.");
        }
        if (bindingResult.hasErrors()) {
            return "/category/category-create";
        }
        categoryService.save(categoryDTO);
        return "redirect:/categories/list";
    }

    //update
    @GetMapping("/update")
    public String update(Model model){
        model.addAttribute("newCategory", new CategoryDTO());
        return "/category/category-update";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute ("newCategory") CategoryDTO categoryDTO, BindingResult bindingResult, @PathVariable Long categoryId, Model model) throws CloneNotSupportedException {
        if(bindingResult.hasErrors()){
            return "redirect:/categories/update/" + categoryId;
        }
        categoryService.update(categoryDTO, categoryId);
        return "redirect:/categories/list";
    }
    //delete
    @GetMapping("/delete/{categoryId}")
    public String delete(@PathVariable("categoryId") Long categoryId, Model model){
        categoryService.delete(categoryService.findById(categoryId));
        return "redirect:/categories/list";
    }



}

