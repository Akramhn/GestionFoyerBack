package com.example.esprit.gestionfoyerback.services;

import com.example.esprit.gestionfoyerback.entities.Chambre;

import java.util.List;

public interface IChambreService {
    List<Chambre> retrieveAllChambres();

    Chambre  addChambre(Chambre c);
    Chambre updateChambre (Chambre  c);

    Chambre retrieveChambre (long idChambre);
}
