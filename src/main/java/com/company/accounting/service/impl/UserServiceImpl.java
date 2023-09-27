package com.company.accounting.service.impl;

import com.company.accounting.dto.UserDTO;
import com.company.accounting.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDTO findByUsername(String name) {
        return null;
    }

    @Override
    public UserDTO getCurrentUser() {
        return null;
    }

    @Override
    public UserDTO findById(Long aLong) {
        return null;
    }

    @Override
    public List<UserDTO> findAll() {
        return null;
    }

    @Override
    public UserDTO findByName(String name) {
        return null;
    }

    @Override
    public void save(UserDTO userDTO) {

    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void update(UserDTO userDTO, Long aLong) {

    }

    @Override
    public boolean isExist(UserDTO userDTO, Long aLong) {
        return false;
    }

    @Override
    public boolean isExist(UserDTO userDTO) {
        return false;
    }
}
