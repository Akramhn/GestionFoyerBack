package com.example.esprit.gestionfoyerback.repository;

import com.example.esprit.gestionfoyerback.entities.Evenement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EvenementRep extends CrudRepository<Evenement,Long> {
    Evenement findByNomEvenement(String nomEvenement) ;
    List<Evenement> findByNomEvenementContainingIgnoreCase(String nomEvenement);
}
