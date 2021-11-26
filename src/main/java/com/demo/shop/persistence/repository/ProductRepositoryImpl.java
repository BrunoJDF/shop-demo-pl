package com.demo.shop.persistence.repository;

import com.demo.shop.domain.dto.ProductDto;
import com.demo.shop.domain.repository.ProductRepository;
import com.demo.shop.persistence.crud.ProductCrudRepository;
import com.demo.shop.persistence.entity.Product;
import com.demo.shop.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductCrudRepository crudRepository;
    private final ProductMapper mapper;

    public ProductRepositoryImpl(ProductCrudRepository repository, ProductMapper mapper) {
        this.crudRepository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<ProductDto> getAll(){
        List<Product> productList = (List<Product>) crudRepository.findAll();
        return mapper.toProducts(productList);
    }

    @Override
    public Optional<List<ProductDto>> getByCategory(long categoryId) {
        List<Product> productList = crudRepository.findByIdCategoryOrderByNameAsc(categoryId);
        return Optional.of(mapper.toProducts(productList));
    }

    @Override
    public Optional<List<ProductDto>> getScarceProduct(int quantity) {
        Optional<List<Product>> productList = crudRepository.findByStockLessThanAndStatus(quantity, true);
        return productList.map(mapper::toProducts);
    }

    @Override
    public Optional<ProductDto> getProduct(long id){
        Optional<Product> product = crudRepository.findById(id);
        return product.map(mapper::toProductDto);
    }

    @Override
    public ProductDto save(ProductDto product) {
        Product entity = mapper.toProduct(product);
        return mapper.toProductDto(crudRepository.save(entity));
    }

    @Override
    public void delete(long id){
        crudRepository.deleteById(id);
    }
}
