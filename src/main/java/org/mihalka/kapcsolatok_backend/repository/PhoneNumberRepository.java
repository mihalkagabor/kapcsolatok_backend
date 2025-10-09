package org.mihalka.kapcsolatok_backend.repository;

import org.mihalka.kapcsolatok_backend.model.entity.PhoneNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumberEntity,Long> {
}
