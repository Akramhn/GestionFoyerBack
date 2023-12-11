package com.example.esprit.gestionfoyerback.services;

import com.example.esprit.gestionfoyerback.entities.Foyer;
import com.example.esprit.gestionfoyerback.entities.Universite;
import com.example.esprit.gestionfoyerback.repository.BlocRepository;
import com.example.esprit.gestionfoyerback.repository.FoyerRepository;
import com.example.esprit.gestionfoyerback.repository.UniversiteRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
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
    public Foyer updateFoyer(Foyer f,long idUni) {
        foyerRepository.save(f);
        Universite  universite=universiteRepository.findById(idUni).orElseThrow(null);
        universite.setFoyer(f);
        System.out.println(idUni);
        universiteRepository.save(universite);
        return f;

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
    public Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniversity) {
        Universite universite = universiteRepository.findById(idUniversity).orElse(null);
        foyerRepository.save(foyer);
        universite.setFoyer(foyer);
        universiteRepository.save(universite);
        return foyer;

    }

    @Override
    public Foyer mettreAjourFoyerEtAffecterAUniversite(Foyer foyer, long idNouvelleUniversite) {
        Universite nouvelleUniversite = universiteRepository.findById(idNouvelleUniversite).orElseThrow(null);

        Universite ancienneUniversite = foyer.getUniversite();

        if (ancienneUniversite != null) {
            ancienneUniversite.setFoyer(null);
            universiteRepository.save(ancienneUniversite);
        }

        foyer.setUniversite(nouvelleUniversite);
        foyerRepository.save(foyer);

        nouvelleUniversite.setFoyer(foyer);
        universiteRepository.save(nouvelleUniversite);

        return foyer;
    }


    @Override
    public Foyer getFoyerByUniv(Universite universite) {
        Foyer foyer = foyerRepository.findByUniversite(universite);

        return foyer ;
    }


}
