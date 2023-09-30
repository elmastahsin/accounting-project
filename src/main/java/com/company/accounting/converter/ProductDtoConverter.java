package com.company.accounting.converter;

import com.company.accounting.dto.ProductDTO;
import com.company.accounting.service.ProductService;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class ProductDtoConverter implements Converter<String, ProductDTO> {
    private final ProductService productService;

    public ProductDtoConverter(ProductService productService) {
        this.productService = productService;
    }

        @Override
        public ProductDTO convert(String id) {
            if (id == null || id.isBlank())
                return null;

    return productService.findById(Long.parseLong(id));
    }

}
