package org.mihalka.kapcsolatok_backend.repository;

import org.mihalka.kapcsolatok_backend.model.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity,Long> {
    List<ContactEntity> findByUserId(Long id);
}
