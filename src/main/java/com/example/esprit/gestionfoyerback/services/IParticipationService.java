package com.example.esprit.gestionfoyerback.services;

import com.example.esprit.gestionfoyerback.entities.Evenement;
import com.example.esprit.gestionfoyerback.entities.Participation;

import java.util.List;

public interface IParticipationService {
    List<Participation> retrieveAllParticipations();

    public Participation addParticipations(long idEvenement , long idEtudiant);

    Participation updateParticipation(Participation participation);

    Participation retrieveParticipation(Long idParticipation);

    void removeParticipation(Long idParticipation);
    public Evenement getEvenementByParticipation(Long idParticipation);
}
