package com.example.esprit.gestionfoyerback.services;

import com.example.esprit.gestionfoyerback.entities.Bloc;
import com.example.esprit.gestionfoyerback.entities.Chambre;
import com.example.esprit.gestionfoyerback.entities.Foyer;
import com.example.esprit.gestionfoyerback.repository.BlocRepository;
import com.example.esprit.gestionfoyerback.repository.ChambreRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
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
    public Bloc affecterChambresABloc(Long numChambre, long idBloc) {
         Bloc bloc =  blocRepository.findById(idBloc).orElseThrow();

        Chambre chambre=chambreRepository.findById(numChambre).orElseThrow(null);


             chambre.setBloc(bloc);
            log.info(" bloc jdid nrmlt"+bloc.getNomBloc());

         return bloc ;

    }

    @Override
    @Scheduled(fixedRate = 60000) //Lezemni nziid l annotation enablescheduling fil main
    public void testSchedulure() {
        List <Bloc> blocs = blocRepository.findAll();

        for(Bloc bloc : blocs){
           log.info(String.valueOf(chambreRepository.findChambresByBloc(bloc).size()));
        }
    }

    @Override
    public List<Bloc>  getBlocByFoyer(Long idFoyer) {
        return  blocRepository.getBlocByFoyer(idFoyer);
    }

}
