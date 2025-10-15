package org.mihalka.kapcsolatok_backend.model.dto;

import jakarta.validation.constraints.NotBlank; // Validációs annotáció importálása
import lombok.Data; // Lombok @Data annotáció importálása (getter, setter, toString, equals, hashCode generálására)

// DTO az új cím létrehozásához
@Data
public class AdressCreateDTO {

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

    // Kapcsolattartó azonosítója, akihez a cím tartozik
    private Long contactId;
}
