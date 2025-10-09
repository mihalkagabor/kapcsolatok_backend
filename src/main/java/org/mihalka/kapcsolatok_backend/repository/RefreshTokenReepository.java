package org.mihalka.kapcsolatok_backend.repository;

import org.mihalka.kapcsolatok_backend.model.entity.RefreshTokenEntity;
import org.mihalka.kapcsolatok_backend.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenReepository extends JpaRepository<RefreshTokenEntity, Long> {

    // Megkeres egy refresh tokent a token érték alapján
    Optional<RefreshTokenEntity> findByToken(String token);

    // Törli az összes refresh tokent egy adott felhasználóhoz
    void deleteByUser(UserEntity user);


}
