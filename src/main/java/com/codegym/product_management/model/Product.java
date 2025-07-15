package com.codegym.product_management.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(min = 5, max = 50, message = "Tên phải từ 5 đến 50 ký tự")
    private String name;

    @NotNull(message = "Giá khởi điểm không được để trống")
    @Min(value = 100000, message = "Giá khởi điểm phải từ 100,000 VNĐ trở lên")
    private Double price;

    @NotBlank(message = "Tình trạng không được để trống")
    private String status;

    @NotNull(message = "Vui lòng chọn loại sản phẩm")
    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}