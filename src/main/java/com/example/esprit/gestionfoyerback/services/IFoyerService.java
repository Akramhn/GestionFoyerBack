package com.example.esprit.gestionfoyerback.services;

import com.example.esprit.gestionfoyerback.entities.Foyer;
import com.example.esprit.gestionfoyerback.entities.Universite;

import java.util.List;

public interface IFoyerService  {

    List<Foyer> retrieveAllFoyers();

    Foyer addFoyer (Foyer f);

    Foyer updateFoyer (Foyer f, long idUni);

    Foyer retrieveFoyer (long  idFoyer);

    void removeFoyer (long idFoyer);

    public Foyer ajouterFoyerEtAffecterAUniversite (Foyer foyer, long idUniversite) ;

    Foyer mettreAjourFoyerEtAffecterAUniversite(Foyer foyer, long idNouvelleUniversite);

    public Foyer getFoyerByUniv(Universite universite);
}
