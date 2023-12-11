package com.example.esprit.gestionfoyerback.services;

import com.example.esprit.gestionfoyerback.entities.Evenement;

import java.util.List;

public interface IEvenementService {
    List<Evenement> retrieveAllEvenements();

    Evenement addEvenements(Evenement evenements);

    Evenement updateEvenement(Evenement evenement);

    Evenement retrieveEvenement(Long idEvenement);

    void removeEvenement(Long idEvenement);
    public List<Evenement> searchEvenementByNom(String nomEvenement);


}
