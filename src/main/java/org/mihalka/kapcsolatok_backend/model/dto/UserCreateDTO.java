package org.mihalka.kapcsolatok_backend.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserCreateDTO {

    @NotBlank(message = "A felhasználó név nem lehet üres")
    private String userName;

    @NotBlank(message = "A jelszó nem lehet üres")
    private String passwordHash;

    private String role;
}
