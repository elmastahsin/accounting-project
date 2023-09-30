package com.company.accounting.controller;


import com.company.accounting.dto.ProductDTO;
import com.company.accounting.enums.ProductUnit;
import com.company.accounting.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.company.accounting.service.ProductService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;


    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String list(Model model) {

        model.addAttribute("products", productService.findAll());

        return "/product/product-list";
    }

    //create GetMapping
    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("newProduct", new ProductDTO());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("productUnits", new ArrayList<>(Arrays.asList(
                ProductUnit.KG,
                ProductUnit.LBS,
                ProductUnit.PCS,
                ProductUnit.FEET,
                ProductUnit.INCH,
                ProductUnit.GALLON,
                ProductUnit.METER
        )));
        return "/product/product-create";

    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("newProduct") ProductDTO productDTO, BindingResult bindingResult, Model model) {
        if (productService.isExist(productDTO)) {
            bindingResult.rejectValue("description", " ", "This product already exists.");
        }
        if (bindingResult.hasErrors()) {
            return "/product/product-create";
        }
        productService.save(productDTO);
        return "redirect:/products/list";
    }

    //UPDATE
    @GetMapping("/update/{productId}")
    public String update(@PathVariable("productId") Long productId, Model model) {

        model.addAttribute("newProduct", new ProductDTO());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("productUnits", new ArrayList<>(Arrays.asList(
                ProductUnit.KG,
                ProductUnit.LBS,
                ProductUnit.PCS,
                ProductUnit.FEET,
                ProductUnit.INCH,
                ProductUnit.GALLON,
                ProductUnit.METER
        )));
        return "/product/product-update";
    }

    @PostMapping("/update/{productId}")
    public String update(@Valid @ModelAttribute("newProduct") ProductDTO productDTO, BindingResult bindingResult, @PathVariable Long productId, Model model) throws CloneNotSupportedException {
        if (bindingResult.hasErrors()) {
            return "redirect:/products/update/" + productId;
        }
        productService.update(productDTO, productId);
        return "redirect:/products/list";
    }

    @GetMapping("/delete/{productId}")
    public String delete(@PathVariable("productId") Long productId, Model model) {
        productService.delete(productService.findById(productId));
        return "redirect:/products/list";
    }

}

