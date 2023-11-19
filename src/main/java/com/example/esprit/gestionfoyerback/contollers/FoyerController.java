package com.example.esprit.gestionfoyerback.contollers;

import com.example.esprit.gestionfoyerback.entities.Foyer;
import com.example.esprit.gestionfoyerback.services.IFoyerService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("foyer")
public class FoyerController {

    @Autowired
    private IFoyerService foyerService ;

    @GetMapping
    public List<Foyer> retrieveAllFoyers(){
        return foyerService.retrieveAllFoyers();
    }

    @PostMapping
    public Foyer addFoyer(@RequestBody Foyer f){
        return foyerService.addFoyer(f);
    }

    @PutMapping
    public Foyer updateFoyer(@RequestBody Foyer f){
        return foyerService.updateFoyer(f) ;
    }

    @GetMapping("{idFoyer}")
    public Foyer retrieveFoyer(@PathVariable long idFoyer){
        return foyerService.retrieveFoyer(idFoyer);
    }

    @DeleteMapping("{idFoyer}")
    public void removeFoyer(@PathVariable long idFoyer){
        foyerService.removeFoyer(idFoyer); ;
    }

    @PostMapping("add/{idUniversity}")
    public Foyer ajouterFoyerEtAffecterAUniversite(@RequestBody Foyer foyer , @PathVariable long idUniversity){
        return foyerService.ajouterFoyerEtAffecterAUniversite(foyer,idUniversity);
    }
}
