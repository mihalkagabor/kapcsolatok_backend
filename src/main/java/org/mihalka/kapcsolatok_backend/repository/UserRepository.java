package org.mihalka.kapcsolatok_backend.repository;

import org.apache.catalina.User;
import org.mihalka.kapcsolatok_backend.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByUserName(String name);
}
