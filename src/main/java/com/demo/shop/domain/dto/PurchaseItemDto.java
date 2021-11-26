package com.demo.shop.domain.dto;

import lombok.Data;

@Data
public class PurchaseItemDto {
    private long productId;
    private int quantity;
    private double total;
    private boolean active;
}
