package com.example.esprit.gestionfoyerback.contollers;

import com.example.esprit.gestionfoyerback.entities.Bloc;
import com.example.esprit.gestionfoyerback.services.IBlocService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("bloc")
@CrossOrigin(origins = "*" )
@Slf4j
public class BlocController {
    @Autowired

    private IBlocService iBlocService;

    @GetMapping
    public List<Bloc> retrieveBlocs(){
        return iBlocService.retrieveBlocs();
    }

    @PostMapping
    public Bloc addBloc(@RequestBody Bloc bloc){
        return iBlocService.addBloc(bloc);
    }

    @PutMapping
    public Bloc updateBloc(@RequestBody Bloc bloc){
        return iBlocService.updateBloc(bloc);
    }

    @GetMapping("{idBloc}")
    public Bloc retrieveBloc(@PathVariable long idBloc){
        return iBlocService.retrieveBloc(idBloc);
    }


    @DeleteMapping("{idBloc}")
    public void removeBloc(@PathVariable long idBloc){
        iBlocService.removeBloc(idBloc);
    }
    @GetMapping("cc/{date}")
    public void dateTest(@PathVariable LocalDate date){
        log.info(String.valueOf(date));
    }


    @PutMapping("{idBloc}/{numChambre}")
    public Bloc affecterChambresABloc(@PathVariable long numChambre,@PathVariable long idBloc){
        return iBlocService.affecterChambresABloc(numChambre,idBloc);
    }

    @GetMapping("bloc/{idFoyer}")
    public List<Bloc> getBlocByFoyer(@PathVariable long idFoyer){
        return iBlocService.getBlocByFoyer(idFoyer);
    }

}
