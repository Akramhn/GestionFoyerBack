package com.example.esprit.gestionfoyerback.services;

import com.example.esprit.gestionfoyerback.entities.Universite;

import java.util.List;

public interface IUniversiteService {
    List<Universite> retrieveAllUniversities();
    void removeUniversite (long idUniversite);


    Universite addUniversite (Universite u);

    Universite updateUniversite (Universite u);

    Universite retrieveUniversite (long idUniversite);

    Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite);

    public Universite desaffecterFoyerAUniversite (long idUniversite) ;


}
