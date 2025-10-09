package org.mihalka.kapcsolatok_backend.sercurity;

import org.mihalka.kapcsolatok_backend.model.entity.UserEntity;
import org.mihalka.kapcsolatok_backend.model.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetail implements UserDetails {

    private final UserEntity user;


    public CustomUserDetail(UserEntity user) {
        this.user = user;
    }

    public UserEntity getUserEntity() {
        return user;
    }

    @Override
    public String getPassword() {
        return user.getPasswordHash();
    }

    @Override
    public String getUsername(){
        return user.getUserName();
    }

    // Fiók lejárat ellenőrzés (mindig true)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Fiók zárolás ellenőrzés (mindig true)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Hitelesítés lejárat ellenőrzés (mindig true)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(user.getRole() !=null){
            UserRole role= user.getRole();
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" +role.name()) );
        }
        return Collections.emptyList();
    }

}
