package org.mihalka.kapcsolatok_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Kezeli az IllegalArgumentException kivételeket
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // 400-as státusz
                .body(ex.getMessage()); // Ez lesz a Postman-ben látható szöveg
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleIRuntime(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // 400-as státusz
                .body(ex.getMessage()); // Ez lesz a Postman-ben látható szöveg
    }

    /**
     * Minden más hibát is elkap (biztonsági háló)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Hiba történt: " + ex.getMessage());
    }
}
