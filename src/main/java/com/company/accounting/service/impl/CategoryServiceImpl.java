package com.company.accounting.service.impl;

import com.company.accounting.dto.CategoryDTO;
import com.company.accounting.entity.Company;
import com.company.accounting.entity.Category;
import com.company.accounting.mapper.MapperUtil;
import com.company.accounting.repository.CategoryRepository;
import com.company.accounting.service.CategoryService;
import com.company.accounting.service.ProductService;
import com.company.accounting.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final MapperUtil mapperUtil;
    private final UserService userService;
    private final ProductService productService;

    public CategoryServiceImpl(CategoryRepository categoryRepository, MapperUtil mapperUtil, UserService userService, ProductService productService) {
        this.categoryRepository = categoryRepository;
        this.mapperUtil = mapperUtil;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public CategoryDTO findById(Long categoryId) {

        return mapperUtil.convert(categoryRepository.findById(categoryId).get(), new CategoryDTO());
    }

    @Override
    public List<CategoryDTO> findAll() {
        Company company = mapperUtil.convert(userService.getCurrentUser().getCompany(), new Company());
        return categoryRepository
                .findAllByCompany(company)
                .stream()
                .sorted(Comparator.comparing(Category::getDescription))
                .map(category -> {

                    CategoryDTO categoryDto = mapperUtil.convert(category, new CategoryDTO());
                    if (hasProducts(category))
                        categoryDto.setHasProduct(true);

                    return categoryDto;

                }).collect(Collectors.toList());
    }

    private boolean hasProducts(Category category) {
        return productService.findAll().stream()
                .filter(productDto -> productDto.getCategory().getDescription().equalsIgnoreCase(category.getDescription()))
                .count() > 0;
    }
    @Override
    public void save(CategoryDTO categoryDTO) {
        Category category = mapperUtil.convert(categoryDTO, new Category());
        Company company = mapperUtil.convert(userService.getCurrentUser().getCompany(), new Company());
        category.setCompany(company);
        categoryRepository.save(category);

    }

    @Override
    public void delete(CategoryDTO categoryDTO) {

        Category category = categoryRepository.findById(categoryDTO.getId()).get();

        if (hasProducts(category))
            return;
        category.setIsDeleted(true);
        category.setDescription(category.getDescription() + "_" + category.getId() + "_DELETED");
        categoryRepository.save(category);

    }

    @Override
    public void update(CategoryDTO categoryDTO, Long categoryId) {
        categoryDTO.setId(categoryId);
        save(categoryDTO);

    }

    @Override
    public boolean isExist(CategoryDTO categoryDTO) {

        return findAll().stream().filter(savedCategory -> savedCategory.getDescription().equalsIgnoreCase(categoryDTO.getDescription())).count() > 0;
    }
}
