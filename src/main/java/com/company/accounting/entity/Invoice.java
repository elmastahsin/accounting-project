package com.company.accounting.entity;

import com.company.accounting.enums.InvoiceStatus;
import com.company.accounting.enums.InvoiceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity


@Table(name = "invoices")
public class Invoice extends BaseEntity{
    private String invoiceNumber;
    @Enumerated(EnumType.STRING)
    private InvoiceStatus invoiceStatus;
    @Enumerated(EnumType.STRING)
    private InvoiceType invoiceType;
    private LocalDate invoiceDate;
    @ManyToOne
    private ClientVendor clientVendor;
    @ManyToOne
    private Company company;


}
