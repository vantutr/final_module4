package com.codegym.product_management.service.impl;

import com.codegym.product_management.model.Product;
import com.codegym.product_management.repository.IProductRepository;
import com.codegym.product_management.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> searchAndPaginate(String name, Long typeId, Double priceFrom, Double priceTo, Pageable pageable) {
        return productRepository.search(name, typeId, priceFrom, priceTo, pageable);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteAllById(List<Long> ids) {
        productRepository.deleteAllById(ids);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
}