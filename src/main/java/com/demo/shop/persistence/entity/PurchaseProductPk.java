package com.demo.shop.persistence.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class PurchaseProductPk implements Serializable {
    @Column(name = "id_compra")
    private long idPurchase;
    @Column(name = "id_producto")
    private long idProduct;
}
