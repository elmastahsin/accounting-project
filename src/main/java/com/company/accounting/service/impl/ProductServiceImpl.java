package com.company.accounting.service.impl;

import com.company.accounting.dto.ProductDTO;
import com.company.accounting.entity.Company;
import com.company.accounting.entity.Product;
import com.company.accounting.service.ProductService;
import org.springframework.stereotype.Service;
import com.company.accounting.repository.ProductRepository;
import com.company.accounting.mapper.MapperUtil;
import com.company.accounting.service.UserService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        //FIND ALL PRODUCT FOR CURRENT COMPANY
        Company company = mapperUtil.convert(userService.getCurrentUser().getCompany(), new Company());
        return productRepository.findAll().stream()
                .filter(product -> product.getCategory().getCompany().getTitle().equals(company.getTitle()))
                .sorted(Comparator.comparing((Product product) -> product.getCategory().getDescription())
                        .thenComparing(Product::getName))
                .map(product -> mapperUtil.convert(product, new ProductDTO())).collect(Collectors.toList());
    }

    @Override
    public void save(ProductDTO productDTO) {
        Product product = mapperUtil.convert(productDTO, new Product());
        product.setQuantityInStock(0);
        productRepository.save(product);

    }

    @Override
    public void delete(ProductDTO productDTO) {
        Product product = productRepository.findById(productDTO.getId()).get();

        if (product.getQuantityInStock() > 0)
            return;

        product.setIsDeleted(true);
        product.setName(product.getName() + " " + product.getId());
        productRepository.save(product);

    }

    @Override
    public void update(ProductDTO productDTO, Long productId) {
        productDTO.setId(productId);
        productDTO.setQuantityInStock(productRepository.findById(productId).get().getQuantityInStock());

        save(productDTO);


    }

    @Override
    public boolean isExist(ProductDTO productDTO) {
        return findAll().stream().filter(savedProduct -> savedProduct.getName().equalsIgnoreCase(productDTO.getName())).count() > 0;
    }
}
