package usuario.usuarios.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import usuario.usuarios.dto.RegistroDto;
import usuario.usuarios.dto.UsuarioResponseDto;
import usuario.usuarios.model.Usuario;
import usuario.usuarios.repository.usuarioRepository;
import usuario.usuarios.dto.LoginDto;




@Service
public class usuarioService {

    private final usuarioRepository usuarioRepository;

    public usuarioService(usuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Page<Usuario> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public UsuarioResponseDto registrarUsuario(RegistroDto dto){

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.nombre());
        usuario.setEmail(dto.email());
        usuario.setPhone(dto.phone());
        usuario.setPassword(dto.password());

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return new UsuarioResponseDto(
            usuarioGuardado.getId(),
            usuarioGuardado.getNombre(),
            usuarioGuardado.getEmail(),
            usuarioGuardado.getPhone()
        );
    }

    @Transactional
    public Usuario loginUsuario(LoginDto dto) {
        Usuario usuario = usuarioRepository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!usuario.getPassword().equals(dto.password())) {
            throw new RuntimeException("Credenciales inválidas");
        }
        return usuario;

    }

    @Transactional
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }




}