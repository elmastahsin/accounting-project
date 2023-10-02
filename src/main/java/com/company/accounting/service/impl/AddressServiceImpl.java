package com.company.accounting.service.impl;

import com.company.accounting.dto.AddressDTO;

import com.company.accounting.mapper.MapperUtil;
import com.company.accounting.repository.AddressRepository;
import com.company.accounting.service.AddressService;
import com.company.accounting.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final MapperUtil mapperUtil;
    private final UserService userService;

    public AddressServiceImpl(AddressRepository addressRepository, MapperUtil mapperUtil, UserService userService) {
        this.addressRepository = addressRepository;
        this.mapperUtil = mapperUtil;
        this.userService = userService;
    }


    @Override
    public AddressDTO findById(Long addressId) {
        throw new IllegalStateException("Not Implemented");
    }

    @Override
    public List<AddressDTO> findAll() {
        throw new IllegalStateException("Not Implemented");
    }

    @Override
    public void save(AddressDTO data) {
        throw new IllegalStateException("Not Implemented");
    }

    @Override
    public void delete(AddressDTO data) {
        throw new IllegalStateException("Not Implemented");

    }

    @Override
    public void update(AddressDTO AddressDTO, Long addressId) {
        throw new IllegalStateException("Not Implemented");

    }

    @Override
    public boolean isExist(AddressDTO AddressDTO) {
        throw new IllegalStateException("Not Implemented");

    }
}
