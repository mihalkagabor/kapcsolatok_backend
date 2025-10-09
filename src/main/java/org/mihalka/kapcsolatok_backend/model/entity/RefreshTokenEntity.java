package org.mihalka.kapcsolatok_backend.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "refresh_tokens")
@Data
public class RefreshTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="refresh_token_id")
    private Long id;


    // A token értéke (egyedi, nem lehet null)
    @Column(nullable = false, unique = true)
    private String token;

    // A token lejárati ideje (nem lehet null)
    @Column(nullable = false)
    private Instant expiryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",nullable = false)
    private UserEntity user;

}
