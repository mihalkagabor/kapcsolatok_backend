package org.mihalka.kapcsolatok_backend.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ContactCreateDTO {

    @NotBlank(message = "A név megadása kötelező")
    private String name;

    @NotNull(message = "A születési dátum megadása kötelező")
    private LocalDate birthDate;

    @NotBlank(message = "Az anyja neve megadása kötelező")
    private String motherName;

    @NotBlank(message = "A TAJ szám megadása kötelező")
    private String tajNumber;

    @NotBlank(message = "Az adóazonosító megadása kötelező")
    private String taxNumber;

    @Email(message = "Érvényes email címet adj meg")
    private String email;


}
