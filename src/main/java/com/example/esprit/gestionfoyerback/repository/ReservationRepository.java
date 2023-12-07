package com.example.esprit.gestionfoyerback.repository;

import com.example.esprit.gestionfoyerback.entities.Chambre;
import com.example.esprit.gestionfoyerback.entities.Etudiant;
import com.example.esprit.gestionfoyerback.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository <Reservation , String> {

   public Boolean existsByEtudiantsCinAndAnneeUnivirsitaireBetween(long etudiants_cin, LocalDate startDate, LocalDate endDate);
    @Query(value = "SELECT * FROM `reservation` r JOIN chambre_reservations cr WHERE r.id_reservation = cr.reservations_id_reservation AND cr.chambre_id_chambre = :idChambre ",nativeQuery = true)
   public  Reservation findByChambre(@Param("idChambre") Long idChambre);
    @Query(value = "SELECT COUNT(*) FROM `reservation_etudiants` WHERE reservations_id_reservation = :ReservationId ",nativeQuery = true)
    public Long getNumberReservation(@Param("ReservationId") String ReservationId);
    @Query(value ="SELECT * FROM `reservation_etudiants` re JOIN etudiant e WHERE re.etudiants_id_etudiant = e.id_etudiant AND e.cin = :cinEtudiant" , nativeQuery = true)
    public Reservation getReservationByCinEt(@Param("cinEtudiant") Long cinEtudiant);

}

