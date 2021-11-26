package com.demo.shop.web.controller;

import com.demo.shop.common.ApiMapping;
import com.demo.shop.domain.dto.PurchaseDto;
import com.demo.shop.domain.service.PurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiMapping.PURCHASE)
public class PurchaseController {
    private final PurchaseService service;

    public PurchaseController(PurchaseService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PurchaseDto>> getAll(){
        return ResponseEntity.of(Optional.of(service.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<PurchaseDto>> getAllByClient(@PathVariable("id") String id){
        return service.getAllByClient(id).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<PurchaseDto> save(@RequestBody PurchaseDto purchaseDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseDto);
    }
}
