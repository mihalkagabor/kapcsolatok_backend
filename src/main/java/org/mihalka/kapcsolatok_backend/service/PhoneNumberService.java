package org.mihalka.kapcsolatok_backend.service;

import jakarta.transaction.Transactional;
import org.mihalka.kapcsolatok_backend.repository.PhoneNumberRepository;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class PhoneNumberService {
    private final PhoneNumberRepository repository;


    public PhoneNumberService(PhoneNumberRepository repository) {
        this.repository = repository;
    }
}
