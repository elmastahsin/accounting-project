package com.company.accounting.service.impl;

import com.company.accounting.dto.InvoiceDTO;
import com.company.accounting.mapper.MapperUtil;
import com.company.accounting.repository.InvoiceRepository;
import com.company.accounting.service.ClientVendorService;
import com.company.accounting.service.InvoiceService;
import com.company.accounting.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InvoiceServiceImpl implements InvoiceService{
    private final InvoiceRepository invoiceRepository;
    private final MapperUtil mapperUtil;
    private final UserService userService;
    private final ClientVendorService clientVendorService;



    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, MapperUtil mapperUtil, UserService userService, ClientVendorService clientVendorService) {
        this.invoiceRepository = invoiceRepository;
        this.mapperUtil = mapperUtil;
        this.userService = userService;
        this.clientVendorService = clientVendorService;
    }

    @Override
    public InvoiceDTO findById(Long invoiceId) {
        return null;
    }

    @Override
    public List<InvoiceDTO> findAll() {
        return null;
    }

    @Override
    public void save(InvoiceDTO invoiceDTO) {

    }

    @Override
    public void delete(InvoiceDTO invoiceDTO) {

    }

    @Override
    public void update(InvoiceDTO invoiceDTO, Long aLong) {

    }

    @Override
    public boolean isExist(InvoiceDTO invoiceDTO) {
        return false;
    }
}