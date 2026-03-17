package Producto.productos.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductoCreateDto(

    @NotBlank @Size(max = 100)
    String sku,

    @NotBlank @Size(max = 255)
    String nombre,

    @NotNull @Positive
    BigDecimal precio
) {

}
