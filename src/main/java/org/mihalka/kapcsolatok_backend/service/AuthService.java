package org.mihalka.kapcsolatok_backend.service;

import jakarta.transaction.Transactional;
import org.mihalka.kapcsolatok_backend.model.entity.RefreshTokenEntity;
import org.mihalka.kapcsolatok_backend.model.entity.UserEntity;
import org.mihalka.kapcsolatok_backend.repository.RefreshTokenReepository;
import org.mihalka.kapcsolatok_backend.repository.UserRepository;
import org.mihalka.kapcsolatok_backend.sercurity.CustomUserDetail;
import org.mihalka.kapcsolatok_backend.sercurity.JwtService;
import org.mihalka.kapcsolatok_backend.sercurity.RefreshTokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service // Spring Service komponens, Bean-ként regisztrálódik
public class AuthService {

    private final AuthenticationManager authenticationManager; // Spring Security autentikáció kezelő
    private final JwtService jwtService; // JWT tokenek generálása és ellenőrzése
    private final RefreshTokenService refreshTokenService; // Refresh token kezelése
    private final RefreshTokenReepository refreshTokenReepository; // Refresh token adatbázis műveletek
    private final UserRepository felhasznaloRepository; // Felhasználókhoz tartozó adatbázis műveletek

    // Konstruktor Dependency Injection-nel
    public AuthService(AuthenticationManager authenticationManager,
                       JwtService jwtService,
                       RefreshTokenService refreshTokenService,
                       RefreshTokenReepository refreshTokenReepository,
                       UserRepository felhasznaloRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.refreshTokenReepository = refreshTokenReepository;
        this.felhasznaloRepository = felhasznaloRepository;
    }

    // Felhasználói bejelentkezés
    public Map<String,String> login(String userName, String password) {
        // UsernamePasswordAuthenticationToken létrehozása
        Authentication auth = new UsernamePasswordAuthenticationToken(userName, password);

        // Autentikáció végrehajtása (ha rossz jelszó, kivételt dob)
        Authentication authentication = authenticationManager.authenticate(auth);

        // A sikeres autentikáció után a CustomUserDetail lekérése
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        UserEntity user = userDetails.getUserEntity(); // Felhasználói entitás lekérése

        // JWT access token generálása
        String accessToken = jwtService.generateToken(user);
        // Refresh token létrehozása
        String refreshToken = refreshTokenService.createRefreshToken(user).getToken();

        // Visszaadjuk a tokeneket Map formátumban
        return Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken
        );
    }

    // Access token frissítése refresh token alapján
    public String refreshAccessToken(String refreshToken) {
        // Refresh token entitás lekérése
        RefreshTokenEntity refreshTokenEntity = refreshTokenService.findByToken(refreshToken);

        // Ellenőrizzük, hogy a token lejárt-e
        if (refreshTokenService.isTokenExpired(refreshTokenEntity)) {
            throw new RuntimeException("A refresh token lejárt, jelentkezzen be újra");
        }

        // Új access token generálása a refresh token felhasználója számára
        UserEntity user = refreshTokenEntity.getUser();
        return jwtService.generateToken(user);
    }

    // Kijelentkezés (refresh token törlése)
    @Transactional
    public void logout(String refreshToken) {
        Optional<RefreshTokenEntity> tokenOpt = refreshTokenReepository.findByToken(refreshToken);
        tokenOpt.ifPresentOrElse(
                refreshTokenReepository::delete, // Ha létezik, törlés az adatbázisból
                () -> { throw new IllegalArgumentException("Refresh Token nem található"); } // Ha nem létezik, kivétel
        );
    }

}
