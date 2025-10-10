package org.mihalka.kapcsolatok_backend.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.mihalka.kapcsolatok_backend.model.entity.UserEntity;

@Data
public class UserListDTO {

    private Long id;

    @NotBlank(message = "A felhasználó név nem lehet üres")
    private String userName;

    @NotBlank(message = "A jelszó nem lehet üres")
    private String passwordHash;

    @NotBlank(message = "A role nem lehet üres")
    private String role;

    public UserListDTO(UserEntity entity) {
        this.id=entity.getId();
        this.userName=entity.getUserName();
        this.passwordHash=entity.getPasswordHash();
        this.role= String.valueOf(entity.getRole());
    }
}
