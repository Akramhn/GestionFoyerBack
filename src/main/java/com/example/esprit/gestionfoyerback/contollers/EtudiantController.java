package com.example.esprit.gestionfoyerback.contollers;


import com.example.esprit.gestionfoyerback.entities.Etudiant;
import com.example.esprit.gestionfoyerback.services.IEtudiantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("etudiant")
@CrossOrigin(origins = "*" )

public class EtudiantController {
    @Autowired
    private IEtudiantService iEtudiantService;

    @GetMapping
    public List<Etudiant> retrieveAllEtudiants(){
        return iEtudiantService.retrieveAllEtudiants();
    }

    @PostMapping
    public List<Etudiant> addEtudiants(@RequestBody List<Etudiant> etudiants){
        log.info("f",etudiants);
        return iEtudiantService.addEtudiants(etudiants);

    }

    @PutMapping
    public Etudiant updateEtudiant(@RequestBody Etudiant e){
        return iEtudiantService.updateEtudiant(e);
    }

    @GetMapping("{idEtudiant}")
    public Etudiant retrieveEtudiant(@PathVariable long idEtudiant){
        return iEtudiantService.retrieveEtudiant(idEtudiant) ;
    }

    @DeleteMapping("{idEtudiant}")
    public void removeEtudiant(@PathVariable long idEtudiant){
        iEtudiantService.retrieveEtudiant(idEtudiant);
    }


}
