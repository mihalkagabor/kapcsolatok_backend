package org.mihalka.kapcsolatok_backend.sercurity;

import lombok.RequiredArgsConstructor;
import org.mihalka.kapcsolatok_backend.model.entity.UserEntity;
import org.mihalka.kapcsolatok_backend.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService  implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername (String username) {
        UserEntity user= repository.findByUserName(username)
                .orElseThrow(()->new UsernameNotFoundException(
                        "User not found with identifier" + username
                ));
        return new CustomUserDetail(user);
    }

}
