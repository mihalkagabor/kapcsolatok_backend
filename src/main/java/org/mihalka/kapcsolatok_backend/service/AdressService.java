package org.mihalka.kapcsolatok_backend.service;

import jakarta.transaction.Transactional;
import org.mihalka.kapcsolatok_backend.repository.AdressRepository;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class AdressService {

    private final AdressRepository repository;

    public AdressService(AdressRepository repository) {
        this.repository = repository;
    }
}
