//package org.mihalka.kapcsolatok_backend.controller;
//
//import lombok.AllArgsConstructor;
//import org.mihalka.kapcsolatok_backend.service.AdressService;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/adress")
//@AllArgsConstructor
//public class AdressController {
//    private final AdressService service;
//}
package org.mihalka.kapcsolatok_backend.controller;

import lombok.RequiredArgsConstructor;
import org.mihalka.kapcsolatok_backend.model.dto.AdressCreateDTO;
import org.mihalka.kapcsolatok_backend.model.dto.AdressListDTO;
import org.mihalka.kapcsolatok_backend.model.dto.AdressModifyDTO;
import org.mihalka.kapcsolatok_backend.service.AdressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adress")
@RequiredArgsConstructor
public class AdressController {

    private final AdressService service;

    /**
     * Cím létrehozása
     */
    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody AdressCreateDTO dto) {
        service.create(dto);
        return ResponseEntity.ok().build();
    }

    /**
     * Címek listázása
     */
    @GetMapping("/list")
    public ResponseEntity<List<AdressListDTO>> list() {
        List<AdressListDTO> adresses =service.list();
        return ResponseEntity.ok(adresses);
    }

    /**
     * Cím módosítása
     */
    @PutMapping("/modify")
    public ResponseEntity<Void> modify( @RequestBody AdressModifyDTO dto) {
        service.modify(dto);
        return ResponseEntity.ok().build();
    }

    /**
     * Cím törlése
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
