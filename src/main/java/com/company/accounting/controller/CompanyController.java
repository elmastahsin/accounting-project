package com.company.accounting.controller;


import com.company.accounting.dto.CompanyDTO;
import com.company.accounting.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;


@Controller
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("companies", companyService.findAll());
        return "company/company-list";
    }
    //create
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("newCompany", new CompanyDTO());
        return "company/company-create";
    }
    //create
    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("newCompany") CompanyDTO companyDTO , BindingResult bindingResult) {
        if (companyService.isExist(companyDTO)) {
         bindingResult.rejectValue("title", " ", "This title already exists.");
        }
      if (bindingResult.hasErrors()) {
          return "company/company-create";
      }
      companyService.save(companyDTO);
      return "redirect:/companies/list";
    }


    //update method
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        model.addAttribute("company", companyService.findById(id));
        return "company/company-update";
    }

    //update method
    @PostMapping("/update/{companyId}")
    public String update ( @Valid @ModelAttribute("company") CompanyDTO companyDTO, BindingResult bindingResult,@PathVariable("companyId") Long companyId,Model model) {
   if (companyService.isExist(companyDTO)) {
       bindingResult.rejectValue("title", " ", "This title already exists.");
   }
   if (bindingResult.hasErrors()) {
        companyDTO.setId(companyId);
       return "company/company-update" + companyId;
   }

   companyService.update(companyDTO,companyId);
   return "redirect:/companies/list";
    }
    //activate method
    @GetMapping("/activate/{companyId}")
    public String activate(@PathVariable("companyId") Long companyId) {
        companyService.activate(companyId);
        return "redirect:/companies/list";
    }
    //deactivate method
    @GetMapping("/deactivate/{companyId}")
    public String deactivate(@PathVariable("companyId") Long companyId) {
        companyService.deactivate(companyId);
        return "redirect:/companies/list";
    }

}
