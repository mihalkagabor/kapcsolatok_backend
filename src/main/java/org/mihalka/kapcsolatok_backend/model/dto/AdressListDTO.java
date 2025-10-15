package org.mihalka.kapcsolatok_backend.model.dto;

import jakarta.validation.constraints.NotBlank; // Validációs annotációk importálása
import lombok.Data; // Lombok @Data annotáció importálása (getter, setter, toString, equals, hashCode generálására)
import org.mihalka.kapcsolatok_backend.model.entity.AdressEntity; // Entitás importálása DTO konstruktorhoz

// Lombok @Data automatikusan generál gettereket, settereket, toString(), equals() és hashCode() metódusokat
@Data
public class AdressListDTO {

    // DTO azonosító
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

    // Kapcsolattartó azonosítója, akihez a cím tartozik
    private Long contactId;

    // Konstruktor az AdressEntity-ből történő DTO létrehozáshoz
    public AdressListDTO(AdressEntity entity) {
        this.id = entity.getId(); // DTO id beállítása az entitásból
        this.zipCode = entity.getZipCode(); // DTO zipCode beállítása
        this.city = entity.getCity(); // DTO city beállítása
        this.street = entity.getStreet(); // DTO street beállítása
        this.houseNumber = entity.getHouseNumber(); // DTO houseNumber beállítása
        this.contactId = entity.getContact().getId(); // DTO contactId beállítása az entitás kapcsolattartójából
    }
}
