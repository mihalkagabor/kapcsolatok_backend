package org.mihalka.kapcsolatok_backend.sercurity;

import org.mihalka.kapcsolatok_backend.model.entity.UserEntity;
import org.mihalka.kapcsolatok_backend.model.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

// Custom UserDetails implementáció Spring Security-hez
// Ez az osztály csomagolja a UserEntity-t, és adja meg a Spring Security által
// elvárt adatokat (username, password, szerepkörök, státuszok)
public class CustomUserDetail implements UserDetails {

    private final UserEntity user; // A felhasználói entitás

    // Konstruktor: beállítja a UserEntity-t
    public CustomUserDetail(UserEntity user) {
        this.user = user;
    }

    // Getter a teljes UserEntity-hez, ha kell további adatokhoz
    public UserEntity getUserEntity() {
        return user;
    }

    // Jelszó lekérése a Spring Security számára
    @Override
    public String getPassword() {
        return user.getPasswordHash();
    }

    // Felhasználónév lekérése a Spring Security számára
    @Override
    public String getUsername() {
        return user.getUserName();
    }

    // Fiók lejárat ellenőrzés (true = nem járt le)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Fiók zárolás ellenőrzés (true = nincs zárolva)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Hitelesítés lejárat ellenőrzés (true = nem járt le)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Felhasználói jogosultságok lekérése
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (user.getRole() != null) {
            UserRole role = user.getRole();
            // Hozzáadjuk a szerepkört a Spring Security-nek (pl. ROLE_ADMIN, ROLE_USER)
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()));
        }
        // Ha nincs szerepkör, üres lista
        return Collections.emptyList();
    }

}
