package com.midesbackend.spring.retotecnico.app.retotecnicoproducto.dto;

import lombok.Data;

import java.util.List;

@Data
public class RespuestaProductoDTO {
    Integer codigoError ;
    String mensajeRespuesta;
    List<ProductoDTO> listaProducto ;

}
