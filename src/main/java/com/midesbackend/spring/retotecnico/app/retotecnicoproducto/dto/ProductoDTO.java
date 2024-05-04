package com.midesbackend.spring.retotecnico.app.retotecnicoproducto.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ProductoDTO {
    private Integer idProducto;
    private String nombre;
    private Date fecRegistro;
}
