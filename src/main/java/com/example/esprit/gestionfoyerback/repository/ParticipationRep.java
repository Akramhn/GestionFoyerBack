package com.example.esprit.gestionfoyerback.repository;

import com.example.esprit.gestionfoyerback.entities.Etudiant;
import com.example.esprit.gestionfoyerback.entities.Participation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ParticipationRep extends CrudRepository<Participation,Long> {

public Optional<Participation> findByEtudiant(Etudiant etudiant);
public List<Participation> findAllBynomEvenement(String nomEvenement);
}
