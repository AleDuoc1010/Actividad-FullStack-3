package Producto.productos.service;

import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import Producto.productos.dto.ProductoCreateDto;
import Producto.productos.dto.ProductoResponseDto;
import Producto.productos.model.Producto;
import Producto.productos.repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    private ProductoResponseDto mapToResponseDto(Producto producto){
        return new ProductoResponseDto(
            producto.getId(),
            producto.getSku(),
            producto.getNombre(),
            producto.getPrecio()
        );
    }

    @Transactional
    public ProductoResponseDto crearProducto(ProductoCreateDto createDto){

        if(productoRepository.existsBySku(createDto.sku())){
            throw new IllegalArgumentException("El SKU ya existe");
        }

        Producto nuevoProducto = new Producto();
        nuevoProducto.setSku(createDto.sku());
        nuevoProducto.setNombre(createDto.nombre());
        nuevoProducto.setPrecio(createDto.precio());

        Producto productoGuardado = productoRepository.save(nuevoProducto);
        return mapToResponseDto(productoGuardado);
    }

    @Transactional(readOnly = true)
    public Page<ProductoResponseDto> findAll(Pageable pageable){
        Page<Producto> paginaProductos = productoRepository.findAll(pageable);
        return paginaProductos.map(this::mapToResponseDto);
    }

    @Transactional
    public void deleteBySku(String sku){
        Producto producto = productoRepository.findBySku(sku)
            .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con SKU: " + sku));
        productoRepository.delete(producto);
    }
}
