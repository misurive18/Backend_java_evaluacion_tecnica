package com.midesbackend.spring.retotecnico.app.retotecnicoproducto.service;

import com.midesbackend.spring.retotecnico.app.retotecnicoproducto.dto.ProductoDTO;
import com.midesbackend.spring.retotecnico.app.retotecnicoproducto.dto.RespuestaProductoDTO;
import org.hibernate.dialect.OracleTypes;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {

    private final JdbcTemplate jdbcTemplate;

    public ProductoService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public RespuestaProductoDTO insertAndListProducts(ProductoDTO productoDTO) {
        try {
            return jdbcTemplate.execute("CALL inventario.sp_insertAndListProducts(?, ?, ?, ?, ?, ?)",
                    (CallableStatement callableStatement) -> {
                        callableStatement.setInt(1, productoDTO.getIdProducto());
                        callableStatement.setString(2, productoDTO.getNombre());
                        callableStatement.setDate(3, productoDTO.getFecRegistro());
                        callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
                        callableStatement.registerOutParameter(5, Types.INTEGER);
                        callableStatement.registerOutParameter(6, Types.VARCHAR);
                        callableStatement.execute();
                        Integer codigoRespueta = (Integer) callableStatement.getObject(5);
                        String mensajeRespuesta = (String) callableStatement.getObject(6);
                        List<ProductoDTO> productList = new ArrayList<>();
                        if (codigoRespueta == 0) {
                            ResultSet rs = (ResultSet) callableStatement.getObject(4);
                            while (rs.next()) {
                                ProductoDTO product = new ProductoDTO();
                                product.setIdProducto(rs.getInt("producto_id"));
                                product.setNombre(rs.getString("nombre"));
                                product.setFecRegistro(rs.getDate("fech_registro"));
                                productList.add(product);
                            }
                        }

                        RespuestaProductoDTO respuestaProductoDTO = new RespuestaProductoDTO();
                        respuestaProductoDTO.setCodigoError(codigoRespueta);
                        respuestaProductoDTO.setMensajeRespuesta(mensajeRespuesta);
                        respuestaProductoDTO.setListaProducto(productList);
                        return respuestaProductoDTO;
                    });
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
