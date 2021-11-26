package com.demo.shop.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity(name = "compras_productos")
public class PurchaseProduct {
    @EmbeddedId
    private PurchaseProductPk id;
    @Column(name = "cantidad")
    private int quantity;
    // no va Column porque el nombre del atributo sigue es el mismo que en bd
    private BigDecimal total;
    @Column(name = "estado")
    private boolean status;
    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "id_compra", insertable = false, updatable = false)
    private PurchaseOrder purchaseOrder;
    @ManyToOne
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private Product product;
}
