package org.mihalka.kapcsolatok_backend.controller;

import lombok.AllArgsConstructor;
import org.mihalka.kapcsolatok_backend.service.AdressService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/adress")
@AllArgsConstructor
public class AdressController {
    private final AdressService service;
}
