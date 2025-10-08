package org.mihalka.kapcsolatok_backend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name="users")
public class UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A jelszó nem lehet üres")
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    // Szerepkör (ADMIN / USER)
    @NotBlank(message = "A szerepkör nem lehet üres")
    @Column(name = "role", nullable = false)
    private String role;

}
