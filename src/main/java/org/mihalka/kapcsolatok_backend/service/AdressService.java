//package org.mihalka.kapcsolatok_backend.service;
//
//import jakarta.transaction.Transactional;
//import org.mihalka.kapcsolatok_backend.model.dto.*;
//import org.mihalka.kapcsolatok_backend.repository.AdressRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Transactional
//@Service
//public class AdressService {
//
//    private final AdressRepository repository;
//
//    public AdressService(AdressRepository repository) {
//        this.repository = repository;
//    }
//
//    public void create(AdressCreateDTO dto) {
//
//    }
//
//    public List<AdressListDTO> list() {
//        return null;
//    }
//
//    public void modify(AdressModifyDTO dto){
//
//    }
//
//    public void delete(Long id){
//
//    }
//
//}

package org.mihalka.kapcsolatok_backend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.mihalka.kapcsolatok_backend.model.dto.*;
import org.mihalka.kapcsolatok_backend.model.entity.AdressEntity;
import org.mihalka.kapcsolatok_backend.model.entity.ContactEntity;
import org.mihalka.kapcsolatok_backend.repository.AdressRepository;
import org.mihalka.kapcsolatok_backend.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class AdressService {

    private final AdressRepository repository;
    private final ContactRepository contactRepository;

    /**
     * Cím létrehozása
     */
    public void create(AdressCreateDTO dto) {
        ContactEntity contact = contactRepository.findById(dto.getContactId())
                .orElseThrow(() -> new IllegalArgumentException("A megadott contact ID nem létezik."));
        AdressEntity adress = new AdressEntity(dto, contact);
        repository.save(adress);
    }

    /**
     * Címek listázása
     */
    public List<AdressListDTO> list() {
        return repository.findAll().stream()
                .sorted(Comparator.comparing(AdressEntity::getCity))
                .map(AdressListDTO::new)
                .toList();
    }

    /**
     * Cím módosítása
     */
    public void modify(AdressModifyDTO dto) {
        AdressEntity entity = repository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("A megadott cím nem található."));

        if (dto.getZipCode() != null) entity.setZipCode(dto.getZipCode());
        if (dto.getCity() != null) entity.setCity(dto.getCity());
        if (dto.getStreet() != null) entity.setStreet(dto.getStreet());
        if (dto.getHouseNumber() != null) entity.setHouseNumber(dto.getHouseNumber());

        repository.save(entity);
    }

    /**
     * Cím törlése
     */
    public void delete(Long id) {
        AdressEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("A megadott cím nem található."));
        repository.delete(entity);
    }
}

