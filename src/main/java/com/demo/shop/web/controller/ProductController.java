package com.demo.shop.web.controller;

import com.demo.shop.common.ApiMapping;
import com.demo.shop.domain.dto.ProductDto;
import com.demo.shop.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiMapping.PRODUCT)
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll(){
        return ResponseEntity.of(Optional.of(service.getAll()));
    }

    @GetMapping("/{id}")
    @ApiOperation("Obtener productos por id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Producto no encontrado"),
    })
    public ResponseEntity<ProductDto> getProduct(@ApiParam(value = "El id del producto", required = true, example = "1") @PathVariable("id") long id){
        return service.getProductById(id).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/by/category/{id}")
    public ResponseEntity<List<ProductDto>> getProductByCategory(@PathVariable("id") long id){
        return service.getByCategory(id).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ProductDto> save(@RequestBody ProductDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id")long id){
        boolean isDeleted = service.deleteProduct(id);
        if(isDeleted){
            return ResponseEntity.accepted().body(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }
}
