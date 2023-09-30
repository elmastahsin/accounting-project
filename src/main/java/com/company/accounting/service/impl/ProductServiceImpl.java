package com.company.accounting.service.impl;

import com.company.accounting.dto.ProductDTO;
import com.company.accounting.service.ProductService;
import org.springframework.stereotype.Service;
import com.company.accounting.repository.ProductRepository;
import com.company.accounting.mapper.MapperUtil;
import com.company.accounting.service.UserService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final MapperUtil mapperUtil;
    private final UserService userService;

    public ProductServiceImpl(ProductRepository productRepository, MapperUtil mapperUtil, UserService userService) {
        this.productRepository = productRepository;
        this.mapperUtil = mapperUtil;
        this.userService = userService;
    }

    @Override
    public ProductDTO findById(Long productId) {
        return mapperUtil.convert(productRepository.findById(productId).get(), new ProductDTO());
    }

    @Override
    public List<ProductDTO> findAll() {
        return null;
    }

    @Override
    public void save(ProductDTO productDTO) {

    }

    @Override
    public void delete(ProductDTO productDTO) {

    }

    @Override
    public void update(ProductDTO productDTO, Long aLong) {

    }

    @Override
    public boolean isExist(ProductDTO productDTO) {
        return false;
    }
}
