package com.company.accounting.service.impl;

import com.company.accounting.dto.InvoiceProductDTO;
import com.company.accounting.service.InvoiceProductService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InvoiceProductServiceImpl implements InvoiceProductService {






    @Override
    public InvoiceProductDTO findById(Long aLong) {
        return null;
    }

    @Override
    public List<InvoiceProductDTO> findAll() {
        return null;
    }

    @Override
    public void save(InvoiceProductDTO invoiceProductDTO) {

    }

    @Override
    public void delete(InvoiceProductDTO invoiceProductDTO) {

    }

    @Override
    public void update(InvoiceProductDTO invoiceProductDTO, Long aLong) {

    }

    @Override
    public boolean isExist(InvoiceProductDTO invoiceProductDTO) {
        return false;
    }
}
