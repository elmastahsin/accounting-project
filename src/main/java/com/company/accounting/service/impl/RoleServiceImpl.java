package com.company.accounting.service.impl;

import com.company.accounting.dto.RoleDTO;
import com.company.accounting.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    @Override
    public List<RoleDTO> getRolesForCurrentUser() {
        return null;
    }

    @Override
    public RoleDTO findById(Long aLong) {
        return null;
    }

    @Override
    public List<RoleDTO> findAll() {
        return null;
    }

    @Override
    public RoleDTO findByName(String name) {
        return null;
    }

    @Override
    public void save(RoleDTO roleDTO) {

    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void update(RoleDTO roleDTO, Long aLong) {

    }

    @Override
    public boolean isExist(RoleDTO roleDTO, Long aLong) {
        return false;
    }

    @Override
    public boolean isExist(RoleDTO roleDTO) {
        return false;
    }
}
