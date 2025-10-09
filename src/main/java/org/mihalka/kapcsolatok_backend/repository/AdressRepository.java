package org.mihalka.kapcsolatok_backend.repository;

import org.mihalka.kapcsolatok_backend.model.entity.AdressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressRepository extends JpaRepository<AdressEntity,Long> {
}
