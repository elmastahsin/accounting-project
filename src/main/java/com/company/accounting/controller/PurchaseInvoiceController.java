package com.company.accounting.controller;

import com.company.accounting.service.ClientVendorService;
import com.company.accounting.service.InvoiceProductService;
import com.company.accounting.service.InvoiceService;
import com.company.accounting.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/purchaseInvoices")
public class PurchaseInvoiceController {

    private final InvoiceService invoiceService;
    private final InvoiceProductService invoiceProductService;
    private final ProductService productService;
    private final ClientVendorService clientVendorService;

    public PurchaseInvoiceController(InvoiceService invoiceService, InvoiceProductService invoiceProductService, ProductService productService, ClientVendorService clientVendorService) {
        this.invoiceService = invoiceService;
        this.invoiceProductService = invoiceProductService;
        this.productService = productService;
        this.clientVendorService = clientVendorService;
    }
    //list
    @GetMapping("/list")
    private String list(Model model){
        model.addAttribute("invoices", invoiceService.findPurchaseInvoices()
        return "/invoice/purchase-invoice-list";
    }





    //create
    //update
    //delete




}

