package org.mihalka.kapcsolatok_backend.service;

import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.mihalka.kapcsolatok_backend.model.dto.UserCreateDTO;
import org.mihalka.kapcsolatok_backend.model.dto.UserListDTO;
import org.mihalka.kapcsolatok_backend.model.dto.UserModifyDTO;
import org.mihalka.kapcsolatok_backend.model.dto.UserProfileDTO;
import org.mihalka.kapcsolatok_backend.model.entity.UserEntity;
import org.mihalka.kapcsolatok_backend.model.enums.UserRole;
import org.mihalka.kapcsolatok_backend.repository.UserRepository;
import org.mihalka.kapcsolatok_backend.sercurity.CustomUserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Transactional
@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void create(UserCreateDTO dto) {
        if(repository.findByUserName(dto.getUserName()).isPresent()){
            throw new IllegalArgumentException("Ez a felhasználó név már foglalt.");
        }
        Long userNumber=repository.count();
        UserEntity user=new UserEntity(dto);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))){
            user.setRole(UserRole.valueOf(dto.getRole()));
        }else {
            user.setRole(UserRole.USER);
        }
        if(userNumber ==0){
            user.setRole(UserRole.ADMIN);
        }
        user.setPasswordHash(passwordEncoder.encode(dto.getPasswordHash()));
        repository.save(user);
    }

    public List<UserListDTO> list() {
        return repository.findAll().stream()
                .sorted(Comparator.comparing(UserEntity::getUserName))
                .map(UserListDTO :: new)
                .toList();
    }

    public void modify(UserModifyDTO dto){
//        if(repository.findByUserName(dto.getUserName()).isEmpty()){
//            throw new IllegalArgumentException("Ez a felhasználó név nem létezik.");
//        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        UserEntity currentUser = userDetails.getUserEntity();
        UserEntity targetUser=repository.findByUserName(dto.getUserName())
                .orElseThrow (()-> new IllegalArgumentException("Ez a felhasználó név nem létezik."));

        if(currentUser.getRole() ==UserRole.ADMIN ){
            if(dto.getUserName() !=null) targetUser.setUserName(dto.getUserName());
            if(dto.getPasswordHash() !=null) targetUser.setPasswordHash(passwordEncoder.encode(dto.getPasswordHash()));
            if(dto.getRole() !=null) targetUser.setRole(UserRole.valueOf(dto.getRole()));
        }else{
            targetUser=currentUser;
            if(dto.getUserName() !=null) targetUser.setUserName(dto.getUserName());
            if(dto.getPasswordHash() !=null) targetUser.setPasswordHash(passwordEncoder.encode(dto.getPasswordHash()));
        }

        repository.save(targetUser);

    }

    @Transactional
    public void delete(Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        UserEntity currentUser = userDetails.getUserEntity();

        if(currentUser.getRole() ==UserRole.ADMIN ){
            UserEntity user =repository.findById(id)
                    .orElseThrow (()-> new RuntimeException("Nincs ilyen felhasználó"));
            repository.delete(user);
            return;   }

            if(!currentUser.getId().equals(id)){
                throw new SecurityException("Csak a saját fiókodat törölheted");
            }

            repository.delete(currentUser);
    }

    public UserProfileDTO me() {
        System.out.println("belépve a servicbe");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("autentikáció kikéerve");
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        UserEntity currentUser = userDetails.getUserEntity();
        System.out.println("user elkészült");
        UserProfileDTO dto= new UserProfileDTO(currentUser);


     return new   UserProfileDTO(currentUser);
    }
}
