package com.codegym.product_management.service;

import com.codegym.product_management.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Page<Product> findAll(Pageable pageable);

    Page<Product> searchAndPaginate(String name, Long typeId, Double priceFrom, Double priceTo, Pageable pageable);

    void save(Product product);

    void deleteAllById(List<Long> ids);

    Optional<Product> findById(Long id);
}