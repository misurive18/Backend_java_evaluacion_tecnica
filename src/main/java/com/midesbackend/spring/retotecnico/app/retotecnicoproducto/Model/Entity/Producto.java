package com.midesbackend.spring.retotecnico.app.retotecnicoproducto.Model.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Date;


@Data
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @Column(name = "producto_id")
    private Integer idProducto;// camelcase

    private String nombre;

    @Column(name = "fech_registro")
    private Date fecRegistro;// notacion snake

    public Producto() {
    }
}
