package com.example.esprit.gestionfoyerback.services;

import com.example.esprit.gestionfoyerback.entities.Bloc;
import com.example.esprit.gestionfoyerback.entities.Foyer;
import com.example.esprit.gestionfoyerback.entities.Universite;
import com.example.esprit.gestionfoyerback.repository.BlocRepository;
import com.example.esprit.gestionfoyerback.repository.FoyerRepository;
import com.example.esprit.gestionfoyerback.repository.UniversiteRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

@AllArgsConstructor
public class IFoyerServiceImp implements IFoyerService {

 private final   FoyerRepository foyerRepository;
 private final   UniversiteRepository universiteRepository;
 private final BlocRepository blocRepository ;


    @Override
    public List<Foyer> retrieveAllFoyers() {

        return (List<Foyer> )foyerRepository.findAll();
    }

    @Override
    public Foyer addFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    @Override
    public Foyer updateFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    @Override
    public Foyer retrieveFoyer(long idFoyer) {
        return foyerRepository.findById(idFoyer).orElseThrow(()->new IllegalArgumentException("no foyer found with this id"));
    }

    @Override
    public void removeFoyer(long idFoyer) {
        foyerRepository.deleteById(idFoyer);

    }

    @Override
    public Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite).orElse(null);
        List<Bloc> blocs = foyer.getBlocs();
        foyerRepository.save(foyer);
        for(Bloc  bloc : blocs ){
            bloc.setFoyer(foyer);

        }
        blocRepository.saveAll(blocs) ;
        universite.setFoyer(foyer);

        universiteRepository.save(universite);

        return foyer ;

    }


}
