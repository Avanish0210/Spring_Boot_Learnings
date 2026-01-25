package com.example.SecurityApp.repositories;

import com.example.SecurityApp.entities.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Integer> {

    Optional<SessionEntity> findByUserId(String userId);
    Optional<SessionEntity> findByUserIdAndToken(String userId, String token);
    void deleteByUserId(String userId);
}
