package com.example.esprit.gestionfoyerback.services;

import com.example.esprit.gestionfoyerback.entities.Etudiant;
import com.example.esprit.gestionfoyerback.entities.Evenement;
import com.example.esprit.gestionfoyerback.entities.Participation;
import com.example.esprit.gestionfoyerback.repository.EvenementRep;
import com.example.esprit.gestionfoyerback.repository.IEtudiantRepository;
import com.example.esprit.gestionfoyerback.repository.ParticipationRep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class IParticipationServiceImplementation implements IParticipationService {

    IEtudiantRepository etudiantRepository;
    private ParticipationRep participationRepository;

    private EvenementRep evenementRep;

    @Override
    public List<Participation> retrieveAllParticipations() {
        return (List<Participation>) participationRepository.findAll();
    }

    @Override
    @Transactional
    public Participation addParticipations(long idEvenement , long idEtudiant) {
        LocalDate dateParticipation = LocalDate.now();

        Etudiant etudiant= etudiantRepository.findById(idEtudiant).orElse(null);
        Evenement evenement= evenementRep.findById(idEvenement).orElse(null);
        Participation participation=
                Participation.builder()
                        .dateParticipation(dateParticipation)
                        .nomEvenement(evenement.getNomEvenement())
                        .etudiant(etudiant)
                        .evenement(new ArrayList<Evenement>()).build()
        ;
        participation.getEvenement().add(evenement);
        return participationRepository.save(participation);
    }

    @Override
    public Participation updateParticipation(Participation participation) {
        return participationRepository.save(participation);
    }

    @Override
    public Participation retrieveParticipation(Long idParticipation) {
        return participationRepository.findById(idParticipation)
                .orElseThrow(() -> new IllegalArgumentException("No Participation Found with this id"));
    }

    @Override
    public void removeParticipation(Long idParticipation) {
        Participation participation = participationRepository.findById(idParticipation).orElseThrow(() -> new IllegalArgumentException("No Participation Found with this id"));

        participationRepository.deleteById(idParticipation);

    }

    @Override
    public Evenement getEvenementByParticipation(Long idParticipation) {
        Participation participation = participationRepository.findById(idParticipation).orElseThrow(() -> new IllegalArgumentException("No Participation Found with this id"));
        return participation.getEvenement().get(0);
    }
}

