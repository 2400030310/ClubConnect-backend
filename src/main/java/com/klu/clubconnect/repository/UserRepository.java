package com.klu.clubconnect.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.klu.clubconnect.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);   // ✅ ONLY THIS
}