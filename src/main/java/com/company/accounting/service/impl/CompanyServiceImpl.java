package com.company.accounting.service.impl;

import com.company.accounting.dto.CompanyDTO;
import com.company.accounting.entity.Company;
import com.company.accounting.enums.CompanyStatus;
import com.company.accounting.mapper.MapperUtil;
import com.company.accounting.repository.CompanyRepository;
import com.company.accounting.service.CompanyService;
import com.company.accounting.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final MapperUtil mapperUtil;
    private final UserService userService;


    public CompanyServiceImpl(CompanyRepository companyRepository, MapperUtil mapperUtil, UserService userService) {
        this.companyRepository = companyRepository;
        this.mapperUtil = mapperUtil;
        this.userService = userService;
    }


    @Override
    public void activate(Long companyId) {
        Company company = companyRepository.findById(companyId).get();
        company.setCompanyStatus(CompanyStatus.ACTIVE);
        companyRepository.save(company);
    }

    @Override
    public void deactivate(Long companyId) {
        Company company = companyRepository.findById(companyId).get();
        company.setCompanyStatus(CompanyStatus.PASSIVE);
        companyRepository.save(company);
    }

    @Override
    public List<CompanyDTO> getCompaniesForCurrentUser() {
        List<Company> companies = companyRepository.findAll();
        if (userService.getCurrentUser().getRole().getDescription().equals("Root User")){
            companies=companyRepository.findAll();
        }else{
            companies = companyRepository.findAll().stream()
                    .filter(company -> company.getTitle().equals(userService.getCurrentUser().getCompany().getTitle()))
                            .collect(Collectors.toList());
        }
        return companies.stream().map(company -> mapperUtil.convert(company, new CompanyDTO())).collect(Collectors.toList());
    }

    @Override
    public CompanyDTO findById(Long aLong) {
        return mapperUtil.convert(companyRepository.findById(aLong).get(), new CompanyDTO());
    }

    @Override
    public List<CompanyDTO> findAll() {
        return companyRepository.findAll().stream()
                .filter(company -> company.getId() != 1)
                .sorted(Comparator.comparing(Company::getCompanyStatus).thenComparing(Company::getTitle))
                .map(each -> mapperUtil.convert(each, new CompanyDTO()))
                .collect(Collectors.toList());


    }



    @Override
    public void save(CompanyDTO companyDTO) {
        companyDTO.setCompanyStatus(CompanyStatus.PASSIVE);
        companyRepository.save(mapperUtil.convert(companyDTO, new Company()));

    }

    @Override
    public void delete(CompanyDTO companyDTO) {
        throw new IllegalStateException("Not Implemented");

    }

    @Override
    public void update(CompanyDTO companyDTO, Long aLong) {
        Company savedCompany = companyRepository.findById(aLong).get();
        companyDTO.setId(aLong);
        companyDTO.setCompanyStatus(savedCompany.getCompanyStatus());
        companyDTO.getAddress().setId(savedCompany.getAddress().getId());
        companyDTO.getAddress().setCountry(savedCompany.getAddress().getCountry());
        Company updatedCompany = mapperUtil.convert(companyDTO, new Company());

        companyRepository.save(updatedCompany);

    }

    @Override
    public boolean isExist(CompanyDTO companyDTO) {
        return findAll().stream().filter(savedCompany -> savedCompany.getTitle().equalsIgnoreCase(companyDTO.getTitle())).count() > 0;
    }



}
