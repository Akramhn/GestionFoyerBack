package com.example.esprit.gestionfoyerback.repository;

import com.example.esprit.gestionfoyerback.entities.Etudiant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IEtudiantRepository extends JpaRepository<Etudiant, Long> {
    Etudiant findByCin(Long cin);
    Etudiant findByEmailContainsIgnoreCase(String email);

    Page<Etudiant> findByEmailContainingIgnoreCase(String searchTerm, Pageable pageable);

    Etudiant findEtudiantByCin(Long cin);
}
