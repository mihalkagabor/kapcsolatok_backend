package org.mihalka.kapcsolatok_backend.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AdressCreateDTO {


    @NotBlank(message = "Az irányítószám megadása kötelező")
    private String zipCode;

    @NotBlank(message = "A város megadása kötelező")
    private String city;

    @NotBlank(message = "Az utca megadása kötelező")
    private String street;

    @NotBlank(message = "A házszám megadása kötelező")
    private String houseNumber;

    private Long contactId;
}
