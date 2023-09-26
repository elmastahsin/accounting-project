package com.company.accounting.entity;

import com.company.accounting.enums.CompanyStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "companies")
public class Company extends BaseEntity {
    @Column(unique = true)
    private String title;

    private String phoneNumber;

    private String webSite;

    @Enumerated(EnumType.STRING)
    private CompanyStatus status;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Address address;


}
