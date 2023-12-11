package com.example.esprit.gestionfoyerback.repository;

import com.example.esprit.gestionfoyerback.entities.Etudiant;
import com.example.esprit.gestionfoyerback.entities.Role;
import com.example.esprit.gestionfoyerback.entities.Universite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface IEtudiantRepository extends JpaRepository<Etudiant, Long> {
    Optional<Etudiant> findByCin(Long cin);
    Etudiant findByEmailContainsIgnoreCase(String email);

    Page<Etudiant> findByEmailContainingIgnoreCase(String searchTerm, Pageable pageable);

    Etudiant findEtudiantByCin(Long cin);
    Page<Etudiant> findEtudiantsByRole(Role role, Pageable pageable);
    List<Etudiant> findEtudiantByUniversite(Universite universite);
}

