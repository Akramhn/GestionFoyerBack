package com.example.esprit.gestionfoyerback.services;

import com.example.esprit.gestionfoyerback.entities.Bloc;
import com.example.esprit.gestionfoyerback.entities.Chambre;
import com.example.esprit.gestionfoyerback.entities.Etudiant;
import com.example.esprit.gestionfoyerback.entities.Reservation;
import com.example.esprit.gestionfoyerback.enums.TypeChambre;
import com.example.esprit.gestionfoyerback.repository.BlocRepository;
import com.example.esprit.gestionfoyerback.repository.ChambreRepository;
import com.example.esprit.gestionfoyerback.repository.IEtudiantRepository;
import com.example.esprit.gestionfoyerback.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class IReservationServiceImp implements IReservationService {

    final ReservationRepository reservationRepository;
    final ChambreRepository chambreRepository ;
    final IEtudiantRepository etudiantRepository ;
    final BlocRepository blocRepository ;
    @Override
    public List<Reservation> retrieveAllReservation() {
        return (List<Reservation>) reservationRepository.findAll();
    }

    @Override
    public Reservation updateReservation(Reservation res) {
        return reservationRepository.save(res);
    }

    @Override
    public Reservation retrieveReservation(String idReservation) {

        return reservationRepository.findById(idReservation).orElseThrow(() -> new IllegalArgumentException("No Reservation Found with this id"));

    }

    @Override
    @Transactional
    public ResponseEntity<String> ajouterReservation(long idChambre, long cinEtudiant) {
        try {
            LocalDate startDate = LocalDate.of(LocalDate.now().getYear(), 1, 1);
            LocalDate endDate = LocalDate.of(LocalDate.now().getYear(), 12, 31);

            // Check if the user already has a reservation
            if (reservationRepository.existsByEtudiantsCinAndAnneeUnivirsitaireBetween(cinEtudiant, startDate, endDate)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You already have a reservation!");
            }

            Chambre chambre = chambreRepository.findById(idChambre).orElseThrow(() -> new IllegalArgumentException("No room found"));

            Etudiant etudiant = etudiantRepository.findByCin(cinEtudiant).orElseThrow(() -> new IllegalArgumentException("No student found"));

            String id = chambre.getNumeroChambre() + "-" + chambre.getBloc().getNomBloc() + "-" + LocalDate.now().getYear();

            Reservation reservation = reservationRepository.findById(id).orElse(
                    Reservation.builder()
                            .idReservation(id)
                            .anneeUnivirsitaire(LocalDate.now())
                            .estValide(true)
                            .etudiants(new ArrayList<>())
                            .build()
            );

            Assert.isTrue(reservation.isEstValide(), "Room not available");
            reservation.getEtudiants().add(etudiant);

            if (!chambre.getReservations().contains(reservation)) {
                reservation = reservationRepository.save(reservation);
                chambre.getReservations().add(reservation);
            }

            switch (chambre.getTypeC()) {
                case SIMPLE:
                    reservation.setEstValide(false);
                    break;
                case DOUBLE:
                    if (reservation.getEtudiants().size() == 2) {
                        reservation.setEstValide(false);
                    }
                    break;
                case TRIPLE:
                    if (reservation.getEtudiants().size() == 3) {
                        reservation.setEstValide(false);
                    }
                    break;
            }

            return ResponseEntity.ok("Reservation added successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }


    @Override
    @Transactional
    public Reservation annulerReservation(long cinEtudiant) {
        Reservation r = reservationRepository.getReservationByCinEt(cinEtudiant);
        r.setEstValide(false);
        r.setEtudiants(null);
        return r ;
    }

}
