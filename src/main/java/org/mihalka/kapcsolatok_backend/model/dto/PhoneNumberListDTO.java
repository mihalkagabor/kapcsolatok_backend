package org.mihalka.kapcsolatok_backend.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.mihalka.kapcsolatok_backend.model.entity.PhoneNumberEntity;

@Data
public class PhoneNumberListDTO {

    private Long id;
    @NotBlank(message = "A telefonszám megadása kötelező")
    private String phoneNumber;

    @NotBlank(message = "A típus megadása kötelező")
    private String type;

    private Long contactId;

    public PhoneNumberListDTO (PhoneNumberEntity entity) {
        this.id=entity.getId();
        this.phoneNumber=entity.getPhoneNumber();
        this.type=entity.getType();
        this.contactId=entity.getContact().getId();
    }
}
