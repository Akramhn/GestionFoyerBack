package com.example.esprit.gestionfoyerback.services;

import com.example.esprit.gestionfoyerback.entities.Bloc;
import com.example.esprit.gestionfoyerback.entities.Chambre;
import com.example.esprit.gestionfoyerback.repository.BlocRepository;
import com.example.esprit.gestionfoyerback.repository.ChambreRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class IBlocServiceImp implements IBlocService {

    private final BlocRepository blocRepository;

  private final ChambreRepository chambreRepository;

    @Override
    public List<Bloc> retrieveBlocs() {
        return (List<Bloc>) blocRepository.findAll();
    }

    @Override
    public Bloc updateBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public Bloc addBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public Bloc retrieveBloc(long idBloc) {
        return blocRepository.findById(idBloc).orElseThrow(()->new IllegalArgumentException("No bloc found with this id"));
    }

    @Override
    public void removeBloc(long idBloc) {
        blocRepository.deleteById(idBloc);

    }

    @Override
    @Transactional
    public Bloc affecterChambresABloc(List<Long> numChambre, long idBloc) {
         Bloc bloc =  blocRepository.findById(idBloc).orElseThrow();


         for(Chambre  chambre: chambreRepository.findChambresByNumeroChambreIn(numChambre)  ){

             chambre.setBloc(bloc);

         }
         return bloc ;

    }

}
