package org.mihalka.kapcsolatok_backend.repository;

import org.mihalka.kapcsolatok_backend.model.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity,Long> {
}
