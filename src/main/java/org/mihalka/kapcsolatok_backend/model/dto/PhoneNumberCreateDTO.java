package org.mihalka.kapcsolatok_backend.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PhoneNumberCreateDTO {
    @NotBlank(message = "A telefonszám megadása kötelező")
    private String phoneNumber;

    @NotBlank(message = "A típus megadása kötelező")
    private String type;

    private Long contactId;
}
