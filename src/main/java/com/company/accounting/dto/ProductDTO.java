package com.company.accounting.dto;

import com.company.accounting.enums.ProductUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;

    private String name;

    private Integer quantityInStock;

    private Integer lowLimitAlert;

    private CategoryDTO category;

    private ProductUnit productUnit;


}

