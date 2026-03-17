package usuario.usuarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import usuario.usuarios.model.Usuario;

@Repository
public interface usuarioRepository extends JpaRepository<Usuario,Long>{

    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);

}
