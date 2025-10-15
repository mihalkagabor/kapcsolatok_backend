package org.mihalka.kapcsolatok_backend.model.dto;

import jakarta.validation.constraints.NotBlank; // Validációs annotációk importálása
import lombok.Data; // Lombok @Data annotáció importálása (getter, setter, toString, equals, hashCode generálására)

// Lombok @Data annotáció automatikusan generálja a gettereket, settereket, toString(), equals() és hashCode() metódusokat
@Data
public class AdressModifyDTO {

    // A módosítandó cím egyedi azonosítója (id)
    private Long id;

    // Irányítószám mező, kötelező
    @NotBlank(message = "Az irányítószám megadása kötelező")
    private String zipCode;

    // Város mező, kötelező
    @NotBlank(message = "A város megadása kötelező")
    private String city;

    // Utca mező, kötelező
    @NotBlank(message = "Az utca megadása kötelező")
    private String street;

    // Házszám mező, kötelező
    @NotBlank(message = "A házszám megadása kötelező")
    private String houseNumber;

}
