package com.example.esprit.gestionfoyerback.services;

import com.example.esprit.gestionfoyerback.entities.Bloc;
import com.example.esprit.gestionfoyerback.entities.Chambre;
import com.example.esprit.gestionfoyerback.repository.BlocRepository;
import com.example.esprit.gestionfoyerback.repository.ChambreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IChambreServiceImp implements  IChambreService {
    @Autowired
    ChambreRepository chambreRepository;

    @Autowired
    BlocRepository blocRepository;


    @Override
    public List<Chambre> retrieveAllChambres() {
        return (List<Chambre>) chambreRepository.findAll();
    }

    @Override
    public Chambre addChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    @Override
    public Chambre updateChambre(Chambre updatedChambre) {
        return chambreRepository.save(updatedChambre);
    }


    @Override
    public Chambre retrieveChambre(long idChambre) {
        return chambreRepository.findById(idChambre).orElseThrow(() -> new IllegalArgumentException("No chambre Found with this id"));
    }
    public void removeChambre (long idFoyer) {
        chambreRepository.deleteById(idFoyer);

    }

    @Override
    public List<Chambre> findChambresByBloc(long idBloc) {
         return chambreRepository.findChambresByBlocIdBloc(idBloc);
    }
}
