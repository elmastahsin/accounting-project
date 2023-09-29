package com.company.accounting.converter;

import com.company.accounting.dto.ClientVendorDTO;

import com.company.accounting.service.ClientVendorService;
import org.springframework.core.convert.converter.Converter;

public class ClientVendorDtoConverter implements Converter<String, ClientVendorDTO> {
    private final ClientVendorService clientVendorService;

    public ClientVendorDtoConverter(ClientVendorService clientVendorService) {
        this.clientVendorService = clientVendorService;
    }

    @Override
    public ClientVendorDTO convert(String id) {

        if (id == null || id.isBlank())
            return null;

        return clientVendorService.findById(Long.parseLong(id));
    }
}
