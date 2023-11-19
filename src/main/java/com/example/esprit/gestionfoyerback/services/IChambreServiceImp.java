package com.example.esprit.gestionfoyerback.services;

import com.example.esprit.gestionfoyerback.entities.Chambre;
import com.example.esprit.gestionfoyerback.repository.ChambreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IChambreServiceImp implements  IChambreService {
    @Autowired
    ChambreRepository chambreRepository;

    @Override
    public List<Chambre> retrieveAllChambres() {
        return (List<Chambre>) chambreRepository.findAll();
    }

    @Override
    public Chambre addChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    @Override
    public Chambre updateChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    @Override
    public Chambre retrieveChambre(long idChambre) {
        return chambreRepository.findById(idChambre).orElseThrow(() -> new IllegalArgumentException("No chambre Found with this id"));
    }
}
