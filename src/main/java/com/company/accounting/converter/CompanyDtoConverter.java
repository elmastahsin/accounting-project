package com.company.accounting.converter;

import com.company.accounting.dto.CompanyDTO;
import com.company.accounting.service.CompanyService;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class CompanyDtoConverter implements Converter<String, CompanyDTO> {
    private final CompanyService companyService;

    public CompanyDtoConverter(CompanyService companyService) {
        this.companyService = companyService;
    }


    @Override
    public CompanyDTO convert(String id) {
        // it throws error if user selects "Select" even with @SneakyThrows
        if(id==null || id.isBlank())
            return null;
        return companyService.findById(Long.parseLong(id));
    }
}
