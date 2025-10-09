package org.mihalka.kapcsolatok_backend.controller;

import lombok.RequiredArgsConstructor;
import org.mihalka.kapcsolatok_backend.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> loginRequest) {
        String userName = loginRequest.get("userName");
        String password = loginRequest.get("password");

        try {
            Map<String, String> tokens = authService.login(userName, password);
            return ResponseEntity.ok(tokens);
        } catch (BadCredentialsException ex) {
            // Hibás felhasználónév/jelszó esetén 401-es státusz és hibaüzenet
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", ex.getMessage()));
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Map<String,String>> refreshAccessToken(@RequestBody Map<String,String> tokenRequest) {
        String refreshToken = tokenRequest.get("refreshToken");
        String newAccessToken = authService.refreshAccessToken(refreshToken);
        return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody Map<String,String> logoutRequest) {
        String refreshToken = logoutRequest.get("refreshToken");
        authService.logout(refreshToken);
        return ResponseEntity.noContent().build();
    }


}
