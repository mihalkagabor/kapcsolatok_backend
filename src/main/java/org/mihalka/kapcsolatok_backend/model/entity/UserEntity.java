package org.mihalka.kapcsolatok_backend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mihalka.kapcsolatok_backend.model.enums.UserRole;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name="users")
public class UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A felhasználó név nem lehet üres")
    @Column(name = "user_name")
    private String userName;

    @NotBlank(message = "A jelszó nem lehet üres")
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    // Szerepkör (ADMIN / USER)
    @NotBlank(message = "A szerepkör nem lehet üres")
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RefreshTokenEntity> refreshToken = new ArrayList<>();

}
