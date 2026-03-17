package usuario.usuarios.service;

import org.springframework.stereotype.Service;

import usuario.usuarios.dto.RegistroDto;
import usuario.usuarios.dto.UsuarioResponseDto;
import usuario.usuarios.model.Usuario;
import usuario.usuarios.repository.usuarioRepository;

@Service
public class usuarioService {

    private final usuarioRepository usuarioRepository;

    public usuarioService(usuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
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
}