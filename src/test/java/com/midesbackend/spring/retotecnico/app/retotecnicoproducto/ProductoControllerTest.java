package com.midesbackend.spring.retotecnico.app.retotecnicoproducto;


import com.midesbackend.spring.retotecnico.app.retotecnicoproducto.controller.ProductoController;
import com.midesbackend.spring.retotecnico.app.retotecnicoproducto.dto.ProductoDTO;
import com.midesbackend.spring.retotecnico.app.retotecnicoproducto.dto.RespuestaProductoDTO;
import com.midesbackend.spring.retotecnico.app.retotecnicoproducto.service.ProductoService;
import com.midesbackend.spring.retotecnico.app.retotecnicoproducto.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductoControllerTest {

    @MockBean
    private ProductoService productoService;

    private ProductoController productoController;

    @BeforeEach
    public void setUp() {
        productoController = new ProductoController(productoService);
    }

    @Test
    public void testGetListProduct() {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setIdProducto(1);
        productoDTO.setNombre("Test Product");
        productoDTO.setFecRegistro(Util.convertirStringToDateSQL("04/05/2024"));

        RespuestaProductoDTO respuestaProductoDTO = new RespuestaProductoDTO();
        respuestaProductoDTO.setCodigoError(0);
        respuestaProductoDTO.setMensajeRespuesta("Ejecucion con  exito");

        when(productoService.insertAndListProducts(productoDTO)).thenReturn(respuestaProductoDTO);

        RespuestaProductoDTO result = productoController.getListProduct(1, "Test Product", "04/05/2024");

        assertEquals(0, result.getCodigoError());
        assertEquals("Ejecucion con  exito", result.getMensajeRespuesta());
    }

    @Test
    public void testGetListProductOk() {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setIdProducto(21);
        productoDTO.setNombre("Test Product");
        productoDTO.setFecRegistro(Util.convertirStringToDateSQL("04/05/2024"));

        RespuestaProductoDTO respuestaProductoDTO = new RespuestaProductoDTO();
        respuestaProductoDTO.setCodigoError(0);
        respuestaProductoDTO.setMensajeRespuesta("Ejecucion con  exito");

        when(productoService.insertAndListProducts(productoDTO)).thenReturn(respuestaProductoDTO);

        RespuestaProductoDTO result = productoController.getListProduct(21, "Test Product", "04/05/2024");

        assertEquals(0, result.getCodigoError());
        assertEquals("Ejecucion con  exito", result.getMensajeRespuesta());
    }

}