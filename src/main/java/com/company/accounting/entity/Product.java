package com.company.accounting.entity;

import com.company.accounting.enums.ProductUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "products")
@Where(clause = "is_deleted=false")
public class Product extends BaseEntity {
    private String name;
    private int quantityInStock;
    private int lowLimitAlert;
    @Enumerated(EnumType.STRING)
    private ProductUnit productUnit;
    @ManyToOne
    private Category category;
}
