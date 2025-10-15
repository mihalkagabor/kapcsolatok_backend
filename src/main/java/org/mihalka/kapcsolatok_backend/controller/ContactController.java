package org.mihalka.kapcsolatok_backend.controller;

import lombok.AllArgsConstructor;
import org.mihalka.kapcsolatok_backend.model.dto.ContactCreateDTO;
import org.mihalka.kapcsolatok_backend.model.dto.ContactListDTO;
import org.mihalka.kapcsolatok_backend.model.dto.ContactModifyDTO;
import org.mihalka.kapcsolatok_backend.service.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@AllArgsConstructor
public class ContactController {

    private final ContactService service;

    /**
     * Új contact létrehozása
     */
    @PostMapping("/create")
    public ResponseEntity<Long> create(@RequestBody ContactCreateDTO dto) {
        Long contactId = service.create(dto);
        return ResponseEntity.ok(contactId);
    }

    /**
     * Contact lista lekérdezése
     */
    @GetMapping("/list")
    public ResponseEntity<List<ContactListDTO>> list() {
        List<ContactListDTO> contacts = service.list();
        return ResponseEntity.ok(contacts);
    }

    /**
     * Contact módosítása
     */
    @PutMapping("/modify")
    public ResponseEntity<Void> modify(@RequestBody ContactModifyDTO dto) {
        service.modify(dto);
        return ResponseEntity.ok().build();
    }

    /**
     * Contact törlése
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
