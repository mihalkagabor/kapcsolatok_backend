//package org.mihalka.kapcsolatok_backend.service;
//
//import jakarta.transaction.Transactional;
//import org.mihalka.kapcsolatok_backend.model.dto.*;
//import org.mihalka.kapcsolatok_backend.repository.PhoneNumberRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Transactional
//@Service
//public class PhoneNumberService {
//    private final PhoneNumberRepository repository;
//
//
//    public PhoneNumberService(PhoneNumberRepository repository) {
//        this.repository = repository;
//    }
//
//    public void create(PhoneNumberCreateDTO dto) {
//
//    }
//
//    public List<PhoneNumberListDTO> list() {
//        return null;
//    }
//
//    public void modify(PhoneNumberModifyDTO dto){
//
//    }
//
//    public void delete(Long id){
//
//    }
//}
//
package org.mihalka.kapcsolatok_backend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.mihalka.kapcsolatok_backend.model.dto.*;
import org.mihalka.kapcsolatok_backend.model.entity.ContactEntity;
import org.mihalka.kapcsolatok_backend.model.entity.PhoneNumberEntity;
import org.mihalka.kapcsolatok_backend.repository.ContactRepository;
import org.mihalka.kapcsolatok_backend.repository.PhoneNumberRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class PhoneNumberService {

    private final PhoneNumberRepository repository;
    private final ContactRepository contactRepository;

    /**
     * Telefonszám létrehozása
     */
    public void create(PhoneNumberCreateDTO dto) {
        ContactEntity contact = contactRepository.findById(dto.getContactId())
                .orElseThrow(() -> new IllegalArgumentException("A megadott contact ID nem létezik."));
        PhoneNumberEntity phone = new PhoneNumberEntity(dto, contact);
        repository.save(phone);
    }

    /**
     * Telefonszámok listázása
     */
    public List<PhoneNumberListDTO> list() {
        return repository.findAll().stream()
                .sorted(Comparator.comparing(PhoneNumberEntity::getPhoneNumber))
                .map(PhoneNumberListDTO::new)
                .toList();
    }

    /**
     * Telefonszám módosítása
     */
    public void modify(PhoneNumberModifyDTO dto) {
        PhoneNumberEntity entity = repository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("A megadott telefonszám nem található."));
        if (dto.getPhoneNumber() != null) entity.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getType() != null) entity.setType(dto.getType());
        repository.save(entity);
    }

    /**
     * Telefonszám törlése
     */
    public void delete(Long id) {
        PhoneNumberEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("A megadott telefonszám nem található."));
        repository.delete(entity);
    }
}
