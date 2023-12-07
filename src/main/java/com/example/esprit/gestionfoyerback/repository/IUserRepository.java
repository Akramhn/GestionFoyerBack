package com.example.esprit.gestionfoyerback.repository;



import com.example.esprit.gestionfoyerback.entities.Role;
import com.example.esprit.gestionfoyerback.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByPasswordResetToken(String passwordResetToken);
    User findByRole(Role role);
}
