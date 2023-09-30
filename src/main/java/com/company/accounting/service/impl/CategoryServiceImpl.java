package com.company.accounting.service.impl;

import com.company.accounting.dto.CategoryDTO;
import com.company.accounting.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Override
    public CategoryDTO findById(Long aLong) {
        return null;
    }

    @Override
    public List<CategoryDTO> findAll() {
        return null;
    }

    @Override
    public void save(CategoryDTO categoryDTO) {

    }

    @Override
    public void delete(CategoryDTO categoryDTO) {

    }

    @Override
    public void update(CategoryDTO categoryDTO, Long aLong) {

    }

    @Override
    public boolean isExist(CategoryDTO categoryDTO) {
        return false;
    }
}
