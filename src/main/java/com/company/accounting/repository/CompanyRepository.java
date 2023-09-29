package com.company.accounting.repository;


import com.company.accounting.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {


    Company findByTitle(String title);
}

