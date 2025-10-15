package org.mihalka.kapcsolatok_backend.model.entity;

import jakarta.persistence.*; // JPA annotációk importálása
import jakarta.validation.constraints.NotBlank; // Validáció importálása
import lombok.Data; // Lombok @Data annotáció
import lombok.NoArgsConstructor; // Lombok @NoArgsConstructor annotáció
import org.mihalka.kapcsolatok_backend.model.dto.UserCreateDTO; // DTO importálása
import org.mihalka.kapcsolatok_backend.model.enums.UserRole; // Enum importálása

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor // Paraméter nélküli konstruktor generálása
@Table(name="users") // Tábla neve az adatbázisban
public class UserEntity {

    // Primer kulcs, auto generált
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Felhasználónév mező, nem lehet üres
    @NotBlank(message = "A felhasználó név nem lehet üres")
    @Column(name = "user_name")
    private String userName;

    // Jelszó hash mező, nem lehet üres
    @NotBlank(message = "A jelszó nem lehet üres")
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    // Szerepkör (ADMIN / USER), Enum típus
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING) // Enum érték stringként tárolódik az adatbázisban
    private UserRole role;

    // Kapcsolat a RefreshToken entitásokkal, one-to-many, cascade és orphanRemoval
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RefreshTokenEntity> refreshToken = new ArrayList<>();

    // Konstruktor DTO alapján, új felhasználó létrehozásához
    public UserEntity(UserCreateDTO dto) {
        this.userName = dto.getUserName();
        this.passwordHash = dto.getPasswordHash();
        this.role = UserRole.valueOf(dto.getRole()); // DTO stringből Enum konverzió
    }
}
