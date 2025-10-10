package org.mihalka.kapcsolatok_backend.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.mihalka.kapcsolatok_backend.model.entity.AdressEntity;

@Data
public class AdressListDTO {
    private Long id;

    @NotBlank(message = "Az irányítószám megadása kötelező")
    private String zipCode;

    @NotBlank(message = "A város megadása kötelező")
    private String city;

    @NotBlank(message = "Az utca megadása kötelező")
    private String street;

    @NotBlank(message = "A házszám megadása kötelező")
    private String houseNumber;

    public AdressListDTO (AdressEntity entity){
        this.id=entity.getId();
        this.zipCode=entity.getZipCode();
        this.city=entity.getCity();
        this.street=entity.getStreet();
        this.houseNumber=entity.getHouseNumber();
    }
}
