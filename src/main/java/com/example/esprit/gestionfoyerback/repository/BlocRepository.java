package com.example.esprit.gestionfoyerback.repository;

import com.example.esprit.gestionfoyerback.entities.Bloc;
import com.example.esprit.gestionfoyerback.entities.Chambre;
import com.example.esprit.gestionfoyerback.entities.Foyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface BlocRepository extends JpaRepository <Bloc,Long> {
    List<Bloc> findByChambresIn(Set<Chambre> chambres);

    @Query("select  b From Bloc b where b.foyer.idFoyer = :idFoyer")
   List <Bloc> getBlocByFoyer(@Param("idFoyer") Long idFoyer);

}
