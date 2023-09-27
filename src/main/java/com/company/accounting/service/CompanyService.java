package com.company.accounting.service;

import com.company.accounting.dto.CompanyDTO;
import com.company.accounting.service.common.CrudService;

import java.util.List;

public interface CompanyService extends CrudService<CompanyDTO, Long> {

    void activate(Long companyId);
    void deactivate(Long companyId);
    List<CompanyDTO> getCompaniesForCurrentUser();

}
