package Producto.productos.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import Producto.productos.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findBySku(String sku);

    boolean existsBySku(String sku);
    
}
