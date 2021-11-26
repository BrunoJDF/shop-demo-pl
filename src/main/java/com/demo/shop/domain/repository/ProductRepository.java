package com.demo.shop.domain.repository;

import com.demo.shop.domain.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<ProductDto> getAll();
    Optional<List<ProductDto>> getByCategory(long categoryId);
    Optional<List<ProductDto>> getScarceProduct(int quantity);
    Optional<ProductDto> getProduct(long productId);
    ProductDto save(ProductDto product);
    void delete(long productId);
}
