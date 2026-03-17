package Producto.productos.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Producto.productos.dto.ProductoCreateDto;
import Producto.productos.dto.ProductoResponseDto;
import Producto.productos.service.ProductoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService){
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<ProductoResponseDto> crearProducto(@Valid @RequestBody ProductoCreateDto createDto){
        ProductoResponseDto nuevoProducto = productoService.crearProducto(createDto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ProductoResponseDto>> getAllProductos(@ParameterObject Pageable pageable){
        Page<ProductoResponseDto> pagina = productoService.findAll(pageable);
        return ResponseEntity.ok(pagina);
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable String sku){
        productoService.deleteBySku(sku);
        return ResponseEntity.noContent().build();
    }

}
