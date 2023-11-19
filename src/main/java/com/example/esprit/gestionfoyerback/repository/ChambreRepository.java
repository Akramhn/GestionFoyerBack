package com.example.esprit.gestionfoyerback.repository;

import com.example.esprit.gestionfoyerback.entities.Chambre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ChambreRepository extends JpaRepository <Chambre , Long > {

    List<Chambre> findChambresByNumeroChambreIn(List<Long> numeroChambre);
}
