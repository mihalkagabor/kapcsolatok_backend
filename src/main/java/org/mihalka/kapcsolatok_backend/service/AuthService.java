package org.mihalka.kapcsolatok_backend.service;

import jakarta.transaction.Transactional;
import org.apache.catalina.User;
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

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final RefreshTokenReepository refreshTokenReepository;
    private final UserRepository felhasznaloRepository;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService, RefreshTokenService refreshTokenService, RefreshTokenReepository refreshTokenReepository, UserRepository felhasznaloRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.refreshTokenReepository = refreshTokenReepository;
        this.felhasznaloRepository = felhasznaloRepository;
    }

    public Map<String,String> login(String userName,  String password) {

        Authentication auth = new UsernamePasswordAuthenticationToken(userName,password);
        Authentication authentication= authenticationManager.authenticate(auth);



        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        UserEntity user = userDetails.getUserEntity(); // ha van getter az entitásra

        String accessToken = jwtService.generateToken(user);
        String refreshToken=refreshTokenService.createRefreshToken(user).getToken();

        return Map.of(
                "accessToken",accessToken,
                "refreshToken", refreshToken
        );
    }

    public String refreshAccessToken(String refreshToken) {
        RefreshTokenEntity refreshTokenEntity =refreshTokenService.findByToken(refreshToken);

        if(refreshTokenService.isTokenExpired(refreshTokenEntity)){
            throw new RuntimeException("A refresh token lejárt, jelentkezzen be újra");
        }

        UserEntity user = refreshTokenEntity.getUser();
        return jwtService.generateToken(user);
    }

    @Transactional
    public void logout(String refreshToken) {
        Optional<RefreshTokenEntity> tokenOpt =refreshTokenReepository.findByToken(refreshToken);
        tokenOpt.ifPresentOrElse(
                refreshTokenReepository :: delete,
                ()-> {throw new IllegalArgumentException("Refresh Token nem található");}
        );
    }

}
