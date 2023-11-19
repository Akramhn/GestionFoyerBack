package com.example.esprit.gestionfoyerback.contollers;

import com.example.esprit.gestionfoyerback.entities.Reservation;
import com.example.esprit.gestionfoyerback.services.IBlocService;
import com.example.esprit.gestionfoyerback.services.IReservationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reservation")
@AllArgsConstructor
public class ReservationController {


    private final IReservationService iReservationService ;

    @GetMapping
    public List<Reservation> retrieveAllReservation(){
        return iReservationService.retrieveAllReservation();
    }

    @PutMapping
    public Reservation updateReservation(@RequestBody Reservation res){
        return iReservationService.updateReservation(res);
    }

    @GetMapping("{idReservation}")
    public Reservation retrieveReservation(@PathVariable String idReservation){
        return iReservationService.retrieveReservation(idReservation);
    }

    @PostMapping("{idChambre}/{cinEtudiant}")
    public Reservation ajouterReservation(@PathVariable long idChambre, @PathVariable long cinEtudiant){
        return iReservationService.ajouterReservation(idChambre , cinEtudiant);
    }

}
