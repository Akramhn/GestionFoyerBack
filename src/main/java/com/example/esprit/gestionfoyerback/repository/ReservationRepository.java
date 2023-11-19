package com.example.esprit.gestionfoyerback.repository;

import com.example.esprit.gestionfoyerback.entities.Chambre;
import com.example.esprit.gestionfoyerback.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository <Reservation , String> {

    @Query(value = "SELECT * FROM `reservation` r JOIN chambre_reservations cr WHERE r.id_reservation = cr.reservations_id_reservation AND cr.chambre_id_chambre = :idChambre ",nativeQuery = true)
   public  Reservation findByChambre(@Param("idChambre") Long idChambre);
    @Query(value = "SELECT COUNT(*) FROM `reservation_etudiants` WHERE reservations_id_reservation = :ReservationId ",nativeQuery = true)
    public Long getNumberReservation(@Param("ReservationId") String ReservationId);}


