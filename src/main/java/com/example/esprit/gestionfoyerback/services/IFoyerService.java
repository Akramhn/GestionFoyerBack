package com.example.esprit.gestionfoyerback.services;

import com.example.esprit.gestionfoyerback.entities.Foyer;

import java.util.List;

public interface IFoyerService  {

    Foyer addFoyer (Foyer f) ;
    List<Foyer> retrieveAllFoyers();

    Foyer updateFoyer(Foyer f);
    Foyer retrieveFoyer (long idFoyer);

    void removeFoyer (long idFoyer);

}
