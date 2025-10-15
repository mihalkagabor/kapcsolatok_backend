package org.mihalka.kapcsolatok_backend.sercurity;

import lombok.RequiredArgsConstructor;
import org.mihalka.kapcsolatok_backend.model.entity.UserEntity;
import org.mihalka.kapcsolatok_backend.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Spring Bean, ami kezeli a felhasználók betöltését a Security számára
@Service
@RequiredArgsConstructor // Lombok annotáció, automatikusan generálja a konstruktor-t a final mezőhöz
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository; // A UserRepository az adatbázisból tölti a felhasználót

    // Spring Security hívja ezt a metódust a bejelentkezéskor
    // username alapján kell visszaadni a UserDetails-t
    @Override
    public UserDetails loadUserByUsername(String username) {
        // Lekérjük az adott felhasználót az adatbázisból
        UserEntity user = repository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found with identifier " + username
                ));

        // A felhasználói entitást becsomagoljuk CustomUserDetail-be
        // Ez adja meg a jelszót, username-t és szerepköröket a Security-nek
        return new CustomUserDetail(user);
    }

}
