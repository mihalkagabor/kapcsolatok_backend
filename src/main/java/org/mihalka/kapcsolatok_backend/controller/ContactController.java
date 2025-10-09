package org.mihalka.kapcsolatok_backend.controller;

import lombok.AllArgsConstructor;
import org.mihalka.kapcsolatok_backend.service.ContactService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contact")
@AllArgsConstructor
public class ContactController {
    private final ContactService service;
}
