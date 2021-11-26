package com.demo.shop.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity(name = "productos")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private long id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "id_categoria")
    private long idCategory;
    @Column(name = "codigo_barras")
    private String barCode;
    @Column(name = "precio_venta")
    private BigDecimal price;
    @Column(name = "cantidad_stock")
    private int stock;
    @Column(name = "estado")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "id_categoria", insertable = false, updatable = false)
    private Category category;
}
