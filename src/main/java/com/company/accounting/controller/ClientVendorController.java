package com.company.accounting.controller;

import com.company.accounting.dto.ClientVendorDTO;
import com.company.accounting.enums.ClientVendorType;
import com.company.accounting.service.ClientVendorService;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/clientVendor")
public class ClientVendorController {

    private final ClientVendorService clientVendorService;

    public ClientVendorController(ClientVendorService clientVendorService) {
        this.clientVendorService = clientVendorService;
    }

    @GetMapping("/list")
    public String list(Model model) {

        model.addAttribute("clientVendors", clientVendorService.findAll());

        return "/clientVendor/clientVendor-list";
    }

    //create
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("newClientVendor", new ClientVendorDTO());
        model.addAttribute("clientVendorTypes", new ArrayList<ClientVendorType>(Arrays.asList(ClientVendorType.CLIENT, ClientVendorType.VENDOR)));
        return "/clientVendor/clientVendor-create";


    }

    //update
    @GetMapping("/update/{clientVendorId}")
    public String update(@PathVariable("clientVendorId") Long clientVendorId, Model model) {
        model.addAttribute("clientVendor", clientVendorService.findById(clientVendorId));
        model.addAttribute("clientVendorTypes", new ArrayList<ClientVendorType>(Arrays.asList(ClientVendorType.CLIENT, ClientVendorType.VENDOR)));
        return "/clientVendor/clientVendor-update";
    }
    @PostMapping("/update/{clientVendorId}")
    public String update(@Valid @ModelAttribute("newClientVendor") ClientVendorDTO clientVendorDTO, BindingResult bindingResult, Model model) {
        if(clientVendorService.isExist(clientVendorDTO)){
            bindingResult.rejectValue("clientVendorName", " ", "This Name already exists.");
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("clientVendorTypes", new ArrayList<ClientVendorType>(Arrays.asList(ClientVendorType.CLIENT, ClientVendorType.VENDOR)));
            return "/clientVendor/clientVendor-update";
        }
        clientVendorService.save(clientVendorDTO);
        return "redirect:/clientVendor/list";
    }

    //delete


}
