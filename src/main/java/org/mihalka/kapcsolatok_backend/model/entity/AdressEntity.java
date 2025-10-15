package org.mihalka.kapcsolatok_backend.model.entity;

import jakarta.persistence.*; // JPA annotációk importálása
import jakarta.validation.constraints.NotBlank; // Validáció importálása
import lombok.Data; // Lombok @Data annotáció (getter, setter, toString, equals, hashCode generálás)
import lombok.NoArgsConstructor; // Lombok @NoArgsConstructor annotáció
import org.mihalka.kapcsolatok_backend.model.dto.AdressCreateDTO; // DTO importja

// AdressEntity entitás, az "adress" tábla reprezentálására
@Entity
@Data
@Table(name = "adress") // Adattábla neve
@NoArgsConstructor // Paraméter nélküli konstruktor generálása
public class AdressEntity {

    // Primer kulcs, auto generált
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Irányítószám mező, kötelező
    @NotBlank(message = "Az irányítószám megadása kötelező")
    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    // Város mező, kötelező
    @NotBlank(message = "A város megadása kötelező")
    @Column(nullable = false)
    private String city;

    // Utca mező, kötelező
    @NotBlank(message = "Az utca megadása kötelező")
    @Column(nullable = false)
    private String street;

    // Házszám mező, kötelező
    @NotBlank(message = "A házszám megadása kötelező")
    @Column(name = "house_number", nullable = false)
    private String houseNumber;

    // Kapcsolat a Contact entitással (ManyToOne)
    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false) // idegen kulcs a contact táblára
    private ContactEntity contact;

    // Konstruktor DTO alapján, új cím létrehozásához
    public AdressEntity(AdressCreateDTO dto, ContactEntity contact){
        this.zipCode = dto.getZipCode();
        this.city = dto.getCity();
        this.street = dto.getStreet();
        this.houseNumber = dto.getHouseNumber();
        this.contact = contact; // kapcsolódó Contact entitás
    }
}
