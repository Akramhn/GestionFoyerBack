package com.example.esprit.gestionfoyerback.services;

import com.example.esprit.gestionfoyerback.entities.Bloc;

import java.util.List;

public interface IBlocService {
 List<Bloc> retrieveBlocs();

 Bloc updateBloc (Bloc  bloc);

 Bloc addBloc (Bloc bloc);

 Bloc retrieveBloc (long  idBloc);

 void removeBloc (long idBloc);

 Bloc affecterChambresABloc(Long numChambre, long idBloc);

 public void testSchedulure();

 public List<Bloc> getBlocByFoyer(Long idFoyer);



}
