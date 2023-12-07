package com.example.esprit.gestionfoyerback.services;

import com.example.esprit.gestionfoyerback.entities.Reservation;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IReservationService  {
    List<Reservation> retrieveAllReservation();


    Reservation updateReservation (Reservation  res);

    Reservation retrieveReservation (String idReservation);

    public Reservation ajouterReservation (long idChambre, long cinEtudiant) ;

    public Reservation annulerReservation (long cinEtudiant) ;

}
