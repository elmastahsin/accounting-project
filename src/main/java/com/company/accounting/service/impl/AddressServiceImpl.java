package com.company.accounting.service.impl;

import com.company.accounting.dto.AddressDTO;
import com.company.accounting.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {


    @Override
    public AddressDTO findById(Long aLong) {
        return null;
    }

    @Override
    public List<AddressDTO> findAll() {
        return null;
    }

    @Override
    public AddressDTO findByName(String name) {
        return null;
    }

    @Override
    public void save(AddressDTO addressDTO) {

    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void update(AddressDTO addressDTO, Long aLong) {

    }

    @Override
    public boolean isExist(AddressDTO addressDTO, Long aLong) {
        return false;
    }

    @Override
    public boolean isExist(AddressDTO addressDTO) {
        return false;
    }
}
