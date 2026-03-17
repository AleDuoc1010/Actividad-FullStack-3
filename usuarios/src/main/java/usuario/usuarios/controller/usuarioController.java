package usuario.usuarios.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import usuario.usuarios.dto.RegistroDto;
import usuario.usuarios.dto.UsuarioResponseDto;
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
}