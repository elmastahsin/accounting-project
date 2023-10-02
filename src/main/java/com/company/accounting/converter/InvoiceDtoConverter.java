package com.company.accounting.converter;

import com.company.accounting.dto.InvoiceDTO;
import com.company.accounting.service.InvoiceService;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class InvoiceDtoConverter implements Converter <String, InvoiceDTO>{
private final InvoiceService invoiceService;

    public InvoiceDtoConverter(@Lazy InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @Override
    public InvoiceDTO convert(String id) {
        if (id == null || id.isBlank())
            return null;
        return invoiceService.findById(Long.parseLong(id));
    }
}
