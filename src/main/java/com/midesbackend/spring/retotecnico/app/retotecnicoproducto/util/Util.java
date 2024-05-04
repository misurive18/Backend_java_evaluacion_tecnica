package com.midesbackend.spring.retotecnico.app.retotecnicoproducto.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Util {
    public static java.sql.Date convertirStringToDateSQL(String fechaString) {
        try {
            // Crear un objeto SimpleDateFormat para analizar la cadena
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

            // Parsear la cadena y obtener un objeto Date
            java.util.Date fechaUtil = formatoFecha.parse(fechaString);

            // Convertir java.util.Date a java.sql.Date
            return new java.sql.Date(fechaUtil.getTime());
        } catch (ParseException e) {
            // Manejar errores de formato de fecha
            e.printStackTrace();
            return null;
        }
    }
}
