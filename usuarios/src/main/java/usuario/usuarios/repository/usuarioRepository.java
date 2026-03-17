package usuario.usuarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import usuario.usuarios.model.Usuario;

@Repository
public interface usuarioRepository extends JpaRepository<Usuario,Long>{

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    @Transactional
    void deleteById(Long id);

}
