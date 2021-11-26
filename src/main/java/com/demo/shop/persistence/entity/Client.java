package com.demo.shop.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "clientes")
public class Client {
    @Id
    private String id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "apellidos")
    private String lastName;
    @Column(name = "celular")
    private long cellphone;
    @Column(name = "direccion")
    private String address;
    @Column(name = "correo_electronico")
    private String email;

    @OneToMany(mappedBy = "client")
    private List<PurchaseOrder> purchaseOrderList;
}
