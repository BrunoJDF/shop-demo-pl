package com.demo.shop.persistence.crud;

import com.demo.shop.persistence.entity.PurchaseOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PurchaseCrudRepository extends CrudRepository<PurchaseOrder, Long> {
    Optional<List<PurchaseOrder>> findByIdClient(String idClient);
}
