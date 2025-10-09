package org.mihalka.kapcsolatok_backend.sercurity;

import jakarta.transaction.Transactional;
import org.mihalka.kapcsolatok_backend.model.entity.RefreshTokenEntity;
import org.mihalka.kapcsolatok_backend.model.entity.UserEntity;
import org.mihalka.kapcsolatok_backend.repository.RefreshTokenReepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    // Refresh token élettartama (milliszekundumban) - application.yml-ben állítható
    @Value("${app.jwt.refreshExpirationMs}")
    private Long refreshTokenDurationMs;

    private final RefreshTokenReepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenReepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    /**
     * Új refresh token létrehozása egy felhasználónak.
     * Előtte törli a korábbi token(eke)t.
     */
    @Transactional
    public RefreshTokenEntity createRefreshToken(UserEntity user){
        refreshTokenRepository.deleteByUser(user);

        RefreshTokenEntity refreshToken =new RefreshTokenEntity();
        refreshToken.setUser(user);
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        return  refreshTokenRepository.save(refreshToken);
    }
    /**
     * Ellenőrzi, hogy a token lejárt-e.
     */
    public boolean isTokenExpired(RefreshTokenEntity token) {
        return token.getExpiryDate().isBefore(Instant.now());
    }

    /**
     * Refresh token kikeresése az adatbázisból.
     * Ha nem található, hibát dob.
     */
    public RefreshTokenEntity findByToken(String token) {
        return refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Refresh token not found: " + token));
    }

}
