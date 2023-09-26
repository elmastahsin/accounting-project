package com.company.accounting.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "users")
public class User extends BaseEntity {
    @Column(unique = true)
    private String username;

    private String password;

    private String firstName;

    private String lastName;
    private String phoneNumber;
    private boolean enabled;

    @ManyToOne
    private Role role;

    @ManyToOne
    private Company company;


}
