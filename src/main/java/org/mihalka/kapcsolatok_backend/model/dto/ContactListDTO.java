package org.mihalka.kapcsolatok_backend.model.dto;

import jakarta.validation.constraints.Email; // Email validációhoz
import jakarta.validation.constraints.NotBlank; // Nem üres String validációhoz
import jakarta.validation.constraints.NotNull; // Nem null érték validációhoz
import lombok.Data; // Lombok @Data annotáció (getter, setter, toString, equals, hashCode generálás)
import org.mihalka.kapcsolatok_backend.model.entity.ContactEntity; // Entitás importja
import java.time.LocalDate; // Születési dátum típusához

// DTO a kapcsolattartók listázásához
@Data
public class ContactListDTO {

    // DTO id mező
    private Long id;

    // Név mező, kötelező
    @NotBlank(message = "A név megadása kötelező")
    private String name;

    // Születési dátum mező, kötelező
    @NotNull(message = "A születési dátum megadása kötelező")
    private LocalDate birthDate;

    // Anyja neve mező, kötelező
    @NotBlank(message = "Az anyja neve megadása kötelező")
    private String motherName;

    // TAJ szám mező, kötelező
    @NotBlank(message = "A TAJ szám megadása kötelező")
    private String tajNumber;

    // Adóazonosító mező, kötelező
    @NotBlank(message = "Az adóazonosító megadása kötelező")
    private String taxNumber;

    // Email mező, érvényes email cím ellenőrzéssel
    @Email(message = "Érvényes email címet adj meg")
    private String email;

    // Konstruktor az entitás DTO-vá alakításához
    public ContactListDTO(ContactEntity entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.birthDate = entity.getBirthDate();
        this.motherName = entity.getMotherName();
        this.tajNumber = entity.getTajNumber();
        this.taxNumber = entity.getTaxNumber();
        this.email = entity.getEmail();
    }
}
