package com.demo.shop.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private long productId;
    private String name;
    private long categoryId;
    private BigDecimal price;
    private int stock;
    private boolean active;

    private CategoryDto category;
}
