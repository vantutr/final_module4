package com.codegym.product_management.service;

import com.codegym.product_management.model.ProductType;

import java.util.List;

public interface IProductTypeService {
    List<ProductType> findAll();
}
