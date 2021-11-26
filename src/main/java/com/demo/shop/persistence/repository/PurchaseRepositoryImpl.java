package com.demo.shop.persistence.repository;

import com.demo.shop.domain.dto.PurchaseDto;
import com.demo.shop.domain.repository.PurchaseRepository;
import com.demo.shop.persistence.crud.PurchaseCrudRepository;
import com.demo.shop.persistence.entity.PurchaseOrder;
import com.demo.shop.persistence.mapper.PurchaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PurchaseRepositoryImpl implements PurchaseRepository {

    private final PurchaseCrudRepository crudRepository;
    private final PurchaseMapper mapper;

    public PurchaseRepositoryImpl(PurchaseCrudRepository crudRepository, PurchaseMapper mapper) {
        this.crudRepository = crudRepository;
        this.mapper = mapper;
    }

    @Override
    public List<PurchaseDto> getAll() {
        return mapper.toPurchaseListDto((List<PurchaseOrder>) crudRepository.findAll());
    }

    @Override
    public Optional<List<PurchaseDto>> getAllByClient(String id) {
        return crudRepository.findByIdClient(id).map(mapper::toPurchaseListDto);
    }

    @Override
    public PurchaseDto save(PurchaseDto purchase) {
        PurchaseOrder forSaved = mapper.toPurchaseOrder(purchase);
        forSaved.getProductList().forEach(purchaseProduct -> purchaseProduct.setPurchaseOrder(forSaved));
        return mapper.toPurchaseDto(crudRepository.save(forSaved));
    }
}
