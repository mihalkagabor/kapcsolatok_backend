//package org.mihalka.kapcsolatok_backend.controller;
//
//import lombok.AllArgsConstructor;
//import org.mihalka.kapcsolatok_backend.repository.PhoneNumberRepository;
//import org.mihalka.kapcsolatok_backend.service.PhoneNumberService;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/phone_number")
//@AllArgsConstructor
//public class PhoneNumberController {
//    private final PhoneNumberService service;
//}
package org.mihalka.kapcsolatok_backend.controller;

import lombok.RequiredArgsConstructor;
import org.mihalka.kapcsolatok_backend.model.dto.PhoneNumberCreateDTO;
import org.mihalka.kapcsolatok_backend.model.dto.PhoneNumberListDTO;
import org.mihalka.kapcsolatok_backend.model.dto.PhoneNumberModifyDTO;
import org.mihalka.kapcsolatok_backend.service.PhoneNumberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phone_number")
@RequiredArgsConstructor
public class PhoneNumberController {

    private final PhoneNumberService service;

    /**
     * Telefonszám létrehozása
     */
    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody PhoneNumberCreateDTO dto) {
        service.create(dto);
        return ResponseEntity.ok().build();
    }

    /**
     * Telefonszámok listázása
     */
    @GetMapping("/list")
    public ResponseEntity<List<PhoneNumberListDTO>> list() {
        return ResponseEntity.ok(service.list());
    }

    /**
     * Telefonszám módosítása
     */
    @PutMapping("/modify")
    public ResponseEntity<Void> modify(@RequestBody PhoneNumberModifyDTO dto) {
        service.modify(dto);
        return ResponseEntity.ok().build();
    }

    /**
     * Telefonszám törlése
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
