package org.mihalka.kapcsolatok_backend.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.mihalka.kapcsolatok_backend.model.entity.ContactEntity;

import java.time.LocalDate;

@Data
public class ContactListDTO {

    private Long id;
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

    public ContactListDTO(ContactEntity entity){
        this.id=entity.getId();
        this.name=entity.getName();
        this.birthDate=entity.getBirthDate();
        this.motherName=entity.getMotherName();
        this.tajNumber=entity.getTajNumber();
        this.taxNumber=entity.getTaxNumber();
        this.email=entity.getEmail();
    }
}
