package com.demo.shop.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "categorias")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private long id;
    @Column(name = "descripcion")
    private String description;
    @Column(name = "estado")
    private boolean status;

    @OneToMany(mappedBy = "category")
    private List<Product> productList;
}
