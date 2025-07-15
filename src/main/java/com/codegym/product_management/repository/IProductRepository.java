package com.codegym.product_management.repository;

import com.codegym.product_management.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE " +
            "(:name IS NULL OR :name = '' OR p.name LIKE %:name%) AND " +
            "(:typeId IS NULL OR p.productType.id = :typeId) AND " +
            "(:priceFrom IS NULL OR p.price >= :priceFrom) AND " +
            "(:priceTo IS NULL OR p.price <= :priceTo)")
    Page<Product> search(
            @Param("name") String name,
            @Param("typeId") Long typeId,
            @Param("priceFrom") Double priceFrom,
            @Param("priceTo") Double priceTo,
            Pageable pageable
    );
}