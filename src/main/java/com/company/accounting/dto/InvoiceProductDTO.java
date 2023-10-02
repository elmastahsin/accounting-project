package com.company.accounting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceProductDTO {

    private Long id;
    private Integer quantity;

    private Integer tax;
    private BigDecimal price;

    private BigDecimal total;
    private BigDecimal profitLoss;
    private Integer remainingQuantity;

    private InvoiceDTO invoice;
    private ProductDTO product;

}
