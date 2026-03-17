package usuario.usuarios.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import usuario.usuarios.dto.LoginDto;
import usuario.usuarios.dto.RegistroDto;
import usuario.usuarios.dto.UsuarioResponseDto;
import usuario.usuarios.model.Usuario;
import usuario.usuarios.service.usuarioService;

@RestController
@RequestMapping("/usuarios")
public class usuarioController {

    private final usuarioService usuarioService;

    public usuarioController(usuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registro")
    public ResponseEntity<UsuarioResponseDto> registrarUsuario(@RequestBody RegistroDto dto){

        UsuarioResponseDto usuario = usuarioService.registrarUsuario(dto);

        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> loginUsuario(@RequestBody LoginDto dto) {
        Usuario usuario = usuarioService.loginUsuario(dto);

        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioResponseDto>> getAllUsuarios(@ParameterObject Pageable pageable) {

        Page<Usuario> paginaUsuarios = usuarioService.findAll(pageable);

        Page<UsuarioResponseDto> paginaDto = paginaUsuarios.map(this::mapToUsuarioResponseDto);
        return ResponseEntity.ok(paginaDto);
    }

    private UsuarioResponseDto mapToUsuarioResponseDto(Usuario usuario) {
        return new UsuarioResponseDto(
            usuario.getId(),
            usuario.getNombre(),
            usuario.getEmail(),
            usuario.getPhone()
        );
    }
}