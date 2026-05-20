package com.cristiandelima.api_login.repository;

import com.cristiandelima.api_login.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID>{
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findById(UUID id);
    Optional<User> findByUsername(String username);
}
