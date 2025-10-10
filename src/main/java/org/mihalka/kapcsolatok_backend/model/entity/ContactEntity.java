package org.mihalka.kapcsolatok_backend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mihalka.kapcsolatok_backend.model.dto.ContactCreateDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "contacts")
public class ContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A név megadása kötelező")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "A születési dátum megadása kötelező")
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @NotBlank(message = "Az anyja neve megadása kötelező")
    @Column(name = "mother_name", nullable = false)
    private String motherName;

    @NotBlank(message = "A TAJ szám megadása kötelező")
    @Column(name = "taj_number", nullable = false)
    private String tajNumber;

    @NotBlank(message = "Az adóazonosító megadása kötelező")
    @Column(name = "tax_number", nullable = false)
    private String taxNumber;

    @Email(message = "Érvényes email címet adj meg")
    @Column(nullable = false)
    private String email;

    // Kapcsolat a felhasználóval
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    // Kapcsolat a címekkel
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdressEntity> addresses = new ArrayList<>();

    // Kapcsolat a telefonszámokkal
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhoneNumberEntity> phoneNumbers = new ArrayList<>();

    public ContactEntity(ContactCreateDTO dto,UserEntity user){
        this.name=dto.getName();
        this.birthDate=dto.getBirthDate();
        this.motherName=dto.getMotherName();
        this.tajNumber=dto.getTajNumber();
        this.taxNumber=dto.getTaxNumber();
        this.email=dto.getEmail();
        this.user=user;
    }

}
