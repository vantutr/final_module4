package com.codegym.product_management.service.impl;

import com.codegym.product_management.model.ProductType;
import com.codegym.product_management.repository.IProductTypeRepository;
import com.codegym.product_management.service.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeService implements IProductTypeService {
    @Autowired
    private IProductTypeRepository productTypeRepository;

    @Override
    public List<ProductType> findAll() {
        return productTypeRepository.findAll();
    }
}