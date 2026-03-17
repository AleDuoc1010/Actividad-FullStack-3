package usuario.usuarios.dto;

public record UsuarioResponseDto(

    Long id,
    String nombre,
    String email,
    String phone
) {
}
