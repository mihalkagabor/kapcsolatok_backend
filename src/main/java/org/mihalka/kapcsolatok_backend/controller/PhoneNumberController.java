package org.mihalka.kapcsolatok_backend.controller;

import lombok.AllArgsConstructor;
import org.mihalka.kapcsolatok_backend.repository.PhoneNumberRepository;
import org.mihalka.kapcsolatok_backend.service.PhoneNumberService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/phone_number")
@AllArgsConstructor
public class PhoneNumberController {
    private final PhoneNumberService service;
}
