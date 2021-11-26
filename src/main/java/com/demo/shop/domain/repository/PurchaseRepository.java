package com.demo.shop.domain.repository;

import com.demo.shop.domain.dto.PurchaseDto;

import java.util.List;
import java.util.Optional;


public interface PurchaseRepository {
    List<PurchaseDto> getAll();
    Optional<List<PurchaseDto>> getAllByClient(String id);
    PurchaseDto save(PurchaseDto purchase);
}
