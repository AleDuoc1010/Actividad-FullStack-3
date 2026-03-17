package usuario.usuarios.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class Usuario {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false)
private String nombre;

@Column(nullable = false, unique = true)
private String email;

@Column(nullable = false)
private String phone;

@Column(nullable = false)
private String password;

}