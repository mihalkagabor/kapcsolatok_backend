package org.mihalka.kapcsolatok_backend.config;

import org.mihalka.kapcsolatok_backend.model.entity.UserEntity;
import org.mihalka.kapcsolatok_backend.model.enums.UserRole;
import org.mihalka.kapcsolatok_backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Ellenőrizzük, hogy létezik-e már admin
        if (userRepository.findAll().isEmpty()) {
            UserEntity admin = new UserEntity();
            admin.setUserName("Admin");
            admin.setPasswordHash(passwordEncoder.encode("Jelszo123!")); // jelszó titkosítva
            admin.setRole(UserRole.ADMIN); // jelszó titkosítva

            userRepository.save(admin);
        }
    }
}


