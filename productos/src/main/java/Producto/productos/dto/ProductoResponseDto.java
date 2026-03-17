package Producto.productos.dto;

import java.math.BigDecimal;

public record ProductoResponseDto (

    Long id,

    String sku,

    String nombre,

    BigDecimal precio
) {}
