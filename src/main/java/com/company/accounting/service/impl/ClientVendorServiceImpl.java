package com.company.accounting.service.impl;

import com.company.accounting.dto.ClientVendorDTO;
import com.company.accounting.entity.Company;
import com.company.accounting.entity.ClientVendor;
import com.company.accounting.mapper.MapperUtil;
import com.company.accounting.repository.ClientVendorRepository;
import com.company.accounting.service.ClientVendorService;
import com.company.accounting.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientVendorServiceImpl implements ClientVendorService {
    private final ClientVendorRepository clientVendorRepository;
    private final MapperUtil mapperUtil;
    private final UserService userService;

    public ClientVendorServiceImpl(ClientVendorRepository clientVendorRepository, MapperUtil mapperUtil, UserService userService) {
        this.clientVendorRepository = clientVendorRepository;
        this.mapperUtil = mapperUtil;
        this.userService = userService;
    }


    @Override
    public ClientVendorDTO findById(Long clientVendorId) {
        return mapperUtil.convert(clientVendorRepository.findById(clientVendorId).get(), new ClientVendorDTO());

    }

    @Override
    public List<ClientVendorDTO> findAll() {
        Company company = mapperUtil.convert(userService.getCurrentUser().getCompany(), new Company());
        return clientVendorRepository.findAllByCompany(company)
                .stream().sorted(Comparator.comparing(ClientVendor::getClientVendorType)
                        .thenComparing(ClientVendor::getClientVendorName))
                .map(each -> mapperUtil.convert(each, new ClientVendorDTO()))
                .collect(Collectors.toList());
    }


    //save method
    @Override
    public void save(ClientVendorDTO clientVendorDto) {

        clientVendorDto.setCompany(userService.getCurrentUser().getCompany());
        clientVendorRepository.save(mapperUtil.convert(clientVendorDto, new ClientVendor()));
    }

    @Override
    public void delete(ClientVendorDTO clientVendorDTO) {
        ClientVendor clientVendor = mapperUtil.convert(clientVendorDTO, new ClientVendor());
        clientVendor.setClientVendorName(clientVendor.getClientVendorName() + "_" + clientVendor.getId() + "_DELETED");

        clientVendor.setIsDeleted(true);
        clientVendorRepository.save(clientVendor);

    }


    @Override
    public void update(ClientVendorDTO clientVendorDTO, Long clientVendorId) {
        clientVendorDTO.setId(clientVendorId);
        ClientVendor updatedClientVendor = mapperUtil.convert(clientVendorDTO, new ClientVendor());
        clientVendorRepository.save(updatedClientVendor);
    }

    @Override
    public boolean isExist(ClientVendorDTO clientVendorDTO) {
        return findAll().stream()
                .filter(clientVendor -> clientVendor.getClientVendorName().equalsIgnoreCase(clientVendorDTO.getClientVendorName()))
                .count() > 0;

    }
}
