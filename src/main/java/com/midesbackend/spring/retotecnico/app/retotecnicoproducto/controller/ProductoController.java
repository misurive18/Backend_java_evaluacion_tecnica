package com.midesbackend.spring.retotecnico.app.retotecnicoproducto.controller;

import com.midesbackend.spring.retotecnico.app.retotecnicoproducto.dto.ProductoDTO;
import com.midesbackend.spring.retotecnico.app.retotecnicoproducto.dto.RespuestaProductoDTO;
import com.midesbackend.spring.retotecnico.app.retotecnicoproducto.service.ProductoService;
import com.midesbackend.spring.retotecnico.app.retotecnicoproducto.util.Util;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/producto/")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService){
        this.productoService=productoService;
    }

    @PostMapping("/lst-product")
    public RespuestaProductoDTO getListProduct(
            @RequestParam("idProducto") Integer idProducto,
            @RequestParam("nombre") String nombre,
            @RequestParam("fecha") String fecha
     ){
         ProductoDTO productoDTO = new ProductoDTO();
         productoDTO.setIdProducto(idProducto);
         productoDTO.setNombre(nombre);
         productoDTO.setFecRegistro(Util.convertirStringToDateSQL(fecha));
         return productoService.insertAndListProducts(productoDTO);
     }
}
