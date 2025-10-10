package org.mihalka.kapcsolatok_backend.controller;

import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.mihalka.kapcsolatok_backend.model.dto.UserCreateDTO;
import org.mihalka.kapcsolatok_backend.model.dto.UserListDTO;
import org.mihalka.kapcsolatok_backend.model.dto.UserModifyDTO;
import org.mihalka.kapcsolatok_backend.repository.UserRepository;
import org.mihalka.kapcsolatok_backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;


    /**
     * 👤 Új felhasználó létrehozása (regisztráció)
     * Csak admin vagy az első felhasználó létrehozásakor elérhető.
     */
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserCreateDTO dto) {
        userService.create(dto);
        return ResponseEntity.ok("A" +dto.getUserName() + "-nevű felhasználó sikeresen létrehozva.");
    }

    /**
     * Felhasználók listázása — csak ADMIN fér hozzá
     */
    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserListDTO>> listUsers() {
        System.out.println("Belépet a végpontra");
        List<UserListDTO> users = userService.list();
        return ResponseEntity.ok(users);
    }

    /**
     * ✏Felhasználó módosítása
     * ADMIN bárkit módosíthat, USER csak saját magát.
     */
    @PutMapping("/modify")
    public ResponseEntity<String> modifyUser(@RequestBody UserModifyDTO dto) {
        System.out.println("Controllerbe lépés");
        userService.modify(dto);
        return ResponseEntity.ok("Felhasználó adatai módosítva.");
    }

    /**
     *  Felhasználó törlése
     * ADMIN bárkit törölhet, USER csak saját magát.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok("Felhasználó törölve.");
    }
}
