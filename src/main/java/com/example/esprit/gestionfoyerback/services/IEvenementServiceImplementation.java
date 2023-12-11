package com.example.esprit.gestionfoyerback.services;

import com.example.esprit.gestionfoyerback.entities.Etudiant;
import com.example.esprit.gestionfoyerback.entities.Evenement;
import com.example.esprit.gestionfoyerback.entities.Participation;
import com.example.esprit.gestionfoyerback.repository.EvenementRep;
import com.example.esprit.gestionfoyerback.repository.ParticipationRep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IEvenementServiceImplementation implements IEvenementService {

    final ParticipationRep participationRep;
    final EvenementRep evenementRep;
    @Override
    public List<Evenement> searchEvenementByNom(String nomEvenement) {
        return evenementRep.findByNomEvenementContainingIgnoreCase(nomEvenement);
    }

    @Override
    public List<Evenement> retrieveAllEvenements() {
        return (List<Evenement>) evenementRep.findAll();
    }


    @Override
    public Evenement addEvenements(Evenement evenements) {
        return (Evenement) evenementRep.save(evenements);
    }

    @Override
    public Evenement updateEvenement(Evenement e) {
        return evenementRep.save(e);
    }

    @Override
    public Evenement retrieveEvenement(Long idEvenement) {
        return evenementRep.findById(idEvenement)
                .orElseThrow(() -> new IllegalArgumentException("No Evenement Found with this id"));
    }

    @Override
    public void removeEvenement(Long idEvenement) {
        Evenement evenement = evenementRep.findById(idEvenement).orElseThrow(() -> new IllegalArgumentException("No Participation Found with this id"));
        List<Participation> participations = participationRep.findAllBynomEvenement(evenement.getNomEvenement());

        participationRep.deleteAll(participations);
        evenementRep.deleteById(idEvenement);
    }

//    @Override
//    @Transactional
//    public Evenement affecterParticipationAEvenement(long idParticipation, String nomEvenement) {
//        Participation participation = participationRep.findById(idParticipation).orElseThrow(null);
//        Evenement evenement = evenementRep.findByNomEvenement(nomEvenement);
//
//        evenement.setParticipations((List<Participation>) participation);
//
//        return evenement;
//    }
//    @Override
//    @Transactional
//    public Evenement desaffecterParticipatioAEvenement(long idEvenement) {
//        Evenement evenement =evenementRep.findById(idEvenement).orElseThrow(null);
//        evenement.setParticipations(null);
//        return evenement;
//    }

}