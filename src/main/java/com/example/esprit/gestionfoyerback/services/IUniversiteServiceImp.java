package com.example.esprit.gestionfoyerback.services;

import com.example.esprit.gestionfoyerback.entities.Etudiant;
import com.example.esprit.gestionfoyerback.entities.Foyer;
import com.example.esprit.gestionfoyerback.entities.Universite;
import com.example.esprit.gestionfoyerback.repository.FoyerRepository;
import com.example.esprit.gestionfoyerback.repository.IEtudiantRepository;
import com.example.esprit.gestionfoyerback.repository.UniversiteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IUniversiteServiceImp implements  IUniversiteService {
    @Autowired
    UniversiteRepository universiteRepository;
    @Autowired
    FoyerRepository foyerRepository;
    @Autowired
    IEtudiantRepository etudiantRepository;
    @Override
    public List<Universite> retrieveAllUniversities() {
        return (List<Universite>) universiteRepository.findAll();
    }

    @Override
    public Universite addUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public Universite updateUniversite(Universite u) {
        u.setFoyer(universiteRepository.findById(u.getIdUniversite()).orElseThrow(null).getFoyer());
        return universiteRepository.save(u);
    }

    @Override
    public Universite retrieveUniversite(long idUniversite) {
        return universiteRepository.findById(idUniversite).orElseThrow(() -> new IllegalArgumentException("No université Found with this id"));
    }



    @Override
    @Transactional
    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite) {
        Foyer foyer = foyerRepository.findById(idFoyer).orElseThrow(null);
        Universite universite = universiteRepository.findByNomUniversite(nomUniversite);

        universite.setFoyer(foyer);

        return universite;
    }

    @Override
    @Transactional
    public Universite desaffecterFoyerAUniversite(long idUniversite) {
         Universite universite =universiteRepository.findById(idUniversite).orElseThrow(null);

         universite.setFoyer(null);
         return universite ;


    }
    @Override
    @Transactional
    public void removeUniversite(long idUniversite) {
        Universite universite=universiteRepository.findById(idUniversite).orElseThrow(null);
        List<Etudiant>etudiant=etudiantRepository.findEtudiantByUniversite(universite);
        for (Etudiant et:etudiant)
        et.setUniversite(null);
        universiteRepository.deleteById(idUniversite);
    }
}
