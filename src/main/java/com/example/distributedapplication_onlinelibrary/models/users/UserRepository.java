package com.example.distributedapplication_onlinelibrary.models.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByEmail(String email);

    boolean existsByEmailAndUserRole(String email, UserRole role);

    @Transactional
    @Modifying
    @Query("UPDATE UserModel a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableUserModel(String email);

}

