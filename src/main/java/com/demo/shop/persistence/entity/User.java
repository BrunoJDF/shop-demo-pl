package com.demo.shop.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private long id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "contrasenia")
    private String password;
    @Column(name = "rol")
    private String role;
}
