package com.demo.shop.persistence.crud;

import com.demo.shop.persistence.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCrudRepository extends CrudRepository<Product, Long> {

    List<Product> findByIdCategoryOrderByNameAsc(long idCategory);

    Optional<List<Product>> findByStockLessThanAndStatus(int stock, boolean status);

    @Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)
    List<Product> getProductByCategory(long idCategory);

}
