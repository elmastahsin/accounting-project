package com.company.accounting.service.impl;

import com.company.accounting.dto.RoleDTO;
import com.company.accounting.entity.Role;
import com.company.accounting.mapper.MapperUtil;
import com.company.accounting.repository.RoleRepository;
import com.company.accounting.service.RoleService;
import com.company.accounting.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final MapperUtil mapperUtil;
    private final UserService userService;

    public RoleServiceImpl(RoleRepository roleRepository, MapperUtil mapperUtil, UserService userService) {
        this.roleRepository = roleRepository;
        this.mapperUtil = mapperUtil;
        this.userService = userService;
    }

    @Override
    public List<RoleDTO> getRolesForCurrentUser() {
        //get list of roles for current user
        List<Role> roles = new ArrayList<>();

        if (userService.getCurrentUser().getRole().getDescription().equals("Root User")) {
            roles = roleRepository.findAll().stream()
                    .filter(role -> role.getDescription().equals("Admin")).collect(Collectors.toList());
        } else {
            roles = roleRepository.findAll().stream()
                    .filter(role -> !role.getDescription().equals("Root User"))
                    .collect(Collectors.toList());
        }
        return roles.stream().map(role -> mapperUtil.convert(role, new RoleDTO())).collect(Collectors.toList());

    }

    @Override
    public RoleDTO findById(Long aLong) {
        throw new IllegalStateException();
    }

    @Override
    public List<RoleDTO> findAll() {
        throw new IllegalStateException("Not Implemented");
    }


    @Override
    public void save(RoleDTO roleDTO) {
        throw new IllegalStateException("Not Implemented");
    }

    @Override
    public void delete(RoleDTO roleDTO) {
        throw new IllegalStateException("Not Implemented");

    }


    @Override
    public void update(RoleDTO roleDTO, Long aLong) {
        throw new IllegalStateException("Not Implemented");

    }


    @Override
    public boolean isExist(RoleDTO roleDTO) {
        throw new IllegalStateException("Not Implemented");
    }
}
