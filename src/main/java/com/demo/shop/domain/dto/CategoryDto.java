package com.demo.shop.domain.dto;

import lombok.Data;

@Data
public class CategoryDto {
    private long categoryId;
    private String category;
    private boolean active;
}
