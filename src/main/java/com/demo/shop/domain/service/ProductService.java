package com.demo.shop.domain.service;

import com.demo.shop.domain.dto.ProductDto;
import com.demo.shop.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductDto> getAll(){
        return repository.getAll();
    }

    public Optional<ProductDto> getProductById(long id){
        return repository.getProduct(id);
    }

    public ProductDto save(ProductDto productDto){
        return repository.save(productDto);
    }

    public Optional<List<ProductDto>> getByCategory(long categoryId){
        return repository.getByCategory(categoryId);
    }

    public boolean deleteProduct(long productId){
        return getProductById(productId).map(dto ->{
            repository.delete(dto.getProductId());
            return true;
        }).orElse(false);
    }
}
