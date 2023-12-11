package com.example.esprit.gestionfoyerback.contollers;

import com.example.esprit.gestionfoyerback.entities.Evenement;
import com.example.esprit.gestionfoyerback.services.IEvenementService;
import com.example.esprit.gestionfoyerback.services.IParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/evenements")
@CrossOrigin(origins ="*" )
public class EvenementController {
    @Autowired
    private IParticipationService participationService;

    @Autowired
    private IEvenementService evenementService;

    @GetMapping
    public ResponseEntity<List<Evenement>> getAllEvenements() {
        List<Evenement> evenements = evenementService.retrieveAllEvenements();
        return ResponseEntity.ok(evenements);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evenement> getEvenementById(@PathVariable Long id) {
        Evenement evenement = evenementService.retrieveEvenement(id);
        return ResponseEntity.ok(evenement);
    }

    @PostMapping(consumes = "multipart/form-data")
    public Evenement addEvenements(@RequestParam("nomEvenement") String nom,
                                   @RequestParam("lieu") String lieu,
                                   @RequestParam("dateEvenement") LocalDate date,
                                   @RequestParam("description") String description,
                                   @RequestParam("image") MultipartFile image
    )throws IOException, SQLException {
        Evenement evenement=new Evenement();
        evenement.setNomEvenement(nom);
        evenement.setLieu(lieu);
        evenement.setDateEvenement(date);
        evenement.setDescription(description);
        byte[] fileBytes = image.getBytes();

        // Convert the byte array to a Blob
        Blob blob = new SerialBlob(fileBytes);

        // Set the Blob in the Universite
        evenement.setImageUrl(blob);
        Evenement addedEvenements = evenementService.addEvenements(evenement);
        return addedEvenements;
    }

    @PutMapping()
    public Evenement updateEvenement(@RequestBody Evenement evenement) {
        return evenementService.updateEvenement(evenement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvenement(@PathVariable Long id) {
        evenementService.removeEvenement(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-participation/{idParticipation}")
    public ResponseEntity<Evenement> getEvenementByParticipation(@PathVariable Long idParticipation) {
            Evenement evenement = participationService.getEvenementByParticipation(idParticipation);
            return ResponseEntity.ok(evenement);

    }
    @PutMapping(consumes = "multipart/form-data")
    public Evenement updateEvenement(@RequestParam("idEvenement") long idEvenement,
                                    @RequestParam("nomEvenement") String nom,
                                     @RequestParam("lieu") String lieu,
                                     @RequestParam("dateEvenement") LocalDate date,
                                     @RequestParam("description") String description,
                                     @RequestParam("image") MultipartFile image
    )throws IOException, SQLException {
        Evenement evenement=new Evenement();
        evenement.setIdEvenement(idEvenement);
        evenement.setNomEvenement(nom);
        evenement.setLieu(lieu);
        evenement.setDateEvenement(date);
        evenement.setDescription(description);
        byte[] fileBytes = image.getBytes();

        // Convert the byte array to a Blob
        Blob blob = new SerialBlob(fileBytes);

        // Set the Blob in the Universite
        evenement.setImageUrl(blob);
        Evenement addedEvenements = evenementService.updateEvenement(evenement);
        return evenementService.updateEvenement(evenement);
    }

    @GetMapping("search/{nomEvenement}")
    public List<Evenement> searchEvenementByNom(@PathVariable String nomEvenement) {
        return evenementService.searchEvenementByNom(nomEvenement);
    }
}
