package org.mihalka.kapcsolatok_backend.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserModifyDTO {
    @NotBlank(message = "A felhasználó név nem lehet üres")
    private String userName;

    @NotBlank(message = "A jelszó nem lehet üres")
    private String passwordHash;

    @NotBlank(message = "A role nem lehet üres")
    private String role;
}
