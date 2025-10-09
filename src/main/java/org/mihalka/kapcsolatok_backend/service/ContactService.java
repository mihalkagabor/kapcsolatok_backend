package org.mihalka.kapcsolatok_backend.service;

import jakarta.transaction.Transactional;
import org.mihalka.kapcsolatok_backend.repository.ContactRepository;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ContactService {

    private final ContactRepository repository;

    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }
}
