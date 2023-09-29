package com.company.accounting.service.impl;

import com.company.accounting.dto.UserDTO;
import com.company.accounting.entity.User;
import com.company.accounting.repository.UserRepository;
import com.company.accounting.mapper.MapperUtil;
import com.company.accounting.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;

    public UserServiceImpl(UserRepository userRepository, MapperUtil mapperUtil) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public UserDTO findByUsername(String username) {

        return  null;

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
    public void save(UserDTO userDTO) {

    }

    @Override
    public void delete(UserDTO userDTO) {

    }


    @Override
    public void update(UserDTO userDTO, Long aLong) {

    }



    @Override
    public boolean isExist(UserDTO userDTO) {
        return false;
    }
}
