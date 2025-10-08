package org.mihalka.kapcsolatok_backend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "adress")
@NoArgsConstructor
public class AdressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "Az irányítószám megadása kötelező")
    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @NotBlank(message = "A város megadása kötelező")
    @Column(nullable = false)
    private String city;

    @NotBlank(message = "Az utca megadása kötelező")
    @Column(nullable = false)
    private String street;

    @NotBlank(message = "A házszám megadása kötelező")
    @Column(name = "house_number", nullable = false)
    private String houseNumber;

    // Kapcsolat a Contact entitással (ManyToOne)
    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false)
    private ContactEntity contact;
}

