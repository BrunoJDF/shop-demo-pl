package com.demo.shop.domain.service;

import com.demo.shop.domain.dto.PurchaseDto;
import com.demo.shop.domain.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    private final PurchaseRepository repository;

    public PurchaseService(PurchaseRepository repository) {
        this.repository = repository;
    }

    public List<PurchaseDto> getAll(){
        return repository.getAll();
    }

    public Optional<List<PurchaseDto>> getAllByClient(String id){
        return repository.getAllByClient(id);
    }

    public PurchaseDto save(PurchaseDto dto){
        return repository.save(dto);
    }
}
