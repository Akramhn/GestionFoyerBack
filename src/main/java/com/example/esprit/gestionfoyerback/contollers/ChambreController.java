package com.example.esprit.gestionfoyerback.contollers;

import com.example.esprit.gestionfoyerback.entities.Chambre;
import com.example.esprit.gestionfoyerback.services.IChambreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("chambre")
@CrossOrigin(origins = "*" )

public class ChambreController {
    @Autowired
    private IChambreService iChambreService;

    @GetMapping
    public List<Chambre> retrieveAllChambres(){
        return iChambreService.retrieveAllChambres();
    }

    @GetMapping("ch/{idBloc}")
    public List<Chambre> getChambreByBloc(@PathVariable long idBloc){
        return iChambreService.findChambresByBloc(idBloc);
    }
    @PostMapping
    public Chambre addChambre(@RequestBody Chambre c){
        return iChambreService.addChambre(c);
    }

    @PutMapping
    public Chambre updateChambre(@RequestBody Chambre c){
        return iChambreService.updateChambre(c);
    }

    @GetMapping("{idChambre}")
    public Chambre retrieveChambre(@PathVariable long idChambre){
        return iChambreService.retrieveChambre(idChambre);
    }

    @DeleteMapping("{idChambre}")
    public void removeFoyer(@PathVariable long idChambre){
        iChambreService.removeChambre(idChambre); ;
    }

}
