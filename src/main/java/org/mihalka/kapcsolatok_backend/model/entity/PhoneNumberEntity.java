package org.mihalka.kapcsolatok_backend.model.entity;

import jakarta.persistence.*; // JPA annotációk importálása
import jakarta.validation.constraints.NotBlank; // Validáció importálása
import lombok.Data; // Lombok @Data annotáció
import lombok.NoArgsConstructor; // Lombok @NoArgsConstructor annotáció
import org.mihalka.kapcsolatok_backend.model.dto.PhoneNumberCreateDTO; // DTO importálása

// PhoneNumberEntity entitás a "phone_numbers" tábla reprezentálására
@Entity
@Data
@Table(name = "phone_numbers") // Tábla neve az adatbázisban
@NoArgsConstructor // Paraméter nélküli konstruktor generálása
public class PhoneNumberEntity {

    // Primer kulcs, auto generált
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Telefonszám mező, kötelező
    @NotBlank(message = "A telefonszám megadása kötelező")
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    // Típus mező (pl. mobil, otthoni), kötelező
    @NotBlank(message = "A típus megadása kötelező")
    @Column(nullable = false)
    private String type;

    // Kapcsolat a Contact entitással (ManyToOne)
    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false) // idegen kulcs a contact táblára
    private ContactEntity contact;

    // Konstruktor DTO alapján, új telefonszám létrehozásához
    public PhoneNumberEntity(PhoneNumberCreateDTO dto, ContactEntity contact) {
        this.phoneNumber = dto.getPhoneNumber();
        this.type = dto.getType();
        this.contact = contact; // kapcsolódó contact
    }
}
