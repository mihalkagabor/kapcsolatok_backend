package org.mihalka.kapcsolatok_backend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.yaml.snakeyaml.events.Event;

@Entity
@Data
@Table(name = "phone_numbers")
@NoArgsConstructor
public class PhoneNumberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "A telefonszám megadása kötelező")
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @NotBlank(message = "A típus megadása kötelező")
    @Column(nullable = false)
    private String type;

    // Kapcsolat a Contact entitással (ManyToOne)
    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false)
    private ContactEntity contact;
}
