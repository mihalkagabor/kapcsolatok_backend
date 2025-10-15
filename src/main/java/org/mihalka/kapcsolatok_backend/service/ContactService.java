package org.mihalka.kapcsolatok_backend.service;

import jakarta.transaction.Transactional;
import org.mihalka.kapcsolatok_backend.model.dto.*;
import org.mihalka.kapcsolatok_backend.model.entity.ContactEntity;
import org.mihalka.kapcsolatok_backend.model.entity.UserEntity;
import org.mihalka.kapcsolatok_backend.model.enums.UserRole;
import org.mihalka.kapcsolatok_backend.repository.ContactRepository;
import org.mihalka.kapcsolatok_backend.repository.UserRepository;
import org.mihalka.kapcsolatok_backend.sercurity.CustomUserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Transactional
@Service
public class ContactService {

    private final ContactRepository repository;

    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }

    public Long create(ContactCreateDTO dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        UserEntity currentUser = userDetails.getUserEntity();

        ContactEntity contact = new ContactEntity(dto,currentUser);
        ContactEntity savedContact = repository.save(contact);

        return savedContact.getId(); // <-- visszaadjuk az új contact azonosítóját
    }

    public List<ContactListDTO> list() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        UserEntity currentUser = userDetails.getUserEntity();

        if(currentUser.getRole()== UserRole.ADMIN){
            return repository.findAll().stream()
                    .sorted(Comparator.comparing(ContactEntity::getId))
                    .map(ContactListDTO::new)
                    .toList();
        }else{
            return repository.findByUserId(currentUser.getId()).stream()
                    .sorted(Comparator.comparing(ContactEntity::getId))
                    .map(ContactListDTO::new)
                    .toList();
        }

    }

    public void modify(ContactModifyDTO dto){
        if(repository.findById(dto.getId()).isEmpty()){
            throw new IllegalArgumentException("Ez a Kontakt nem létezik.");
        }
        ContactEntity contact=repository.findById(dto.getId())
                .orElseThrow(()-> new RuntimeException("Hiba történt"));

        if(dto.getName() !=null) contact.setName(dto.getName());
        if(dto.getMotherName() !=null) contact.setMotherName(dto.getMotherName());
        if(dto.getEmail() !=null) contact.setEmail(dto.getEmail());
        if(dto.getTajNumber() !=null) contact.setTajNumber(dto.getTajNumber());
        if(dto.getTaxNumber() !=null) contact.setTaxNumber(dto.getTaxNumber());
        if(dto.getBirthDate() !=null) contact.setBirthDate(dto.getBirthDate());

        repository.save(contact);

    }

    public void delete(Long id){
        if(repository.findById(id).isEmpty()){
            throw new IllegalArgumentException("Ez a Kontakt nem létezik.");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        UserEntity currentUser = userDetails.getUserEntity();
        ContactEntity entity=repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Gebasz van"));

        if(entity.getUser() ==currentUser){
            repository.delete(entity);
            return;
        }

        if(currentUser.getRole()==UserRole.ADMIN){
            repository.delete(entity);
            return;
        }

    }
}

