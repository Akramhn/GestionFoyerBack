package com.example.esprit.gestionfoyerback.repository;

import com.example.esprit.gestionfoyerback.entities.Bloc;
import com.example.esprit.gestionfoyerback.entities.Chambre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface BlocRepository extends JpaRepository <Bloc,Long> {
    List<Bloc> findByChambresIn(Set<Chambre> chambres);

}
