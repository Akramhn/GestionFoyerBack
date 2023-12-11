package com.example.esprit.gestionfoyerback.contollers;

import com.example.esprit.gestionfoyerback.entities.Participation;
import com.example.esprit.gestionfoyerback.services.IParticipationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participations")
@AllArgsConstructor
@CrossOrigin(origins ="*" )
public class ParticipationController {

    @Autowired
    private IParticipationService participationService;

    @GetMapping
    public ResponseEntity<List<Participation>> getAllParticipations() {
        List<Participation> participations = participationService.retrieveAllParticipations();
        return ResponseEntity.ok(participations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participation> getParticipationById(@PathVariable Long id) {
        Participation participation = participationService.retrieveParticipation(id);
        return ResponseEntity.ok(participation);
    }

    @PostMapping("{idEvenement}/{idEtudiant}")
    public ResponseEntity<Participation> addParticipation(
            @PathVariable long idEvenement,
            @PathVariable long idEtudiant){
        Participation newParticipation = participationService.addParticipations(idEvenement, idEtudiant);
        return new ResponseEntity<>(newParticipation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Participation> updateParticipation(@PathVariable Long id, @RequestBody Participation participation) {
        participation.setIdParticipation(id);
        Participation updatedParticipation = participationService.updateParticipation(participation);
        return ResponseEntity.ok(updatedParticipation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipation(@PathVariable Long id) {
        participationService.removeParticipation(id);
        return ResponseEntity.noContent().build();
    }
}
