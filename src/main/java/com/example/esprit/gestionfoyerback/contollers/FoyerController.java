package com.example.esprit.gestionfoyerback.contollers;

import com.example.esprit.gestionfoyerback.entities.Foyer;
import com.example.esprit.gestionfoyerback.entities.Universite;
import com.example.esprit.gestionfoyerback.services.IFoyerService;
import com.example.esprit.gestionfoyerback.services.IUniversiteService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("foyer")
@CrossOrigin(origins = "*" )
@AllArgsConstructor
public class FoyerController {

    @Autowired
    private IFoyerService foyerService ;
    private IUniversiteService iUniversiteService ;

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

    @PostMapping(value = "/adduni/{idUniversity}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public Foyer ajouterFoyerEtAffecterAUniversite(@RequestParam("nomFoyer") String nomFoyer , @RequestParam("capacite") long capacite, @RequestParam("image") MultipartFile image, @PathVariable long idUniversity)throws IOException, SQLException {
        Foyer foyer=new Foyer();
        foyer.setNomFoyer(nomFoyer);
        foyer.setCapaciteFoyer(capacite);
        byte[] fileBytes = image.getBytes();

        // Convert the byte array to a Blob
        Blob blob = new SerialBlob(fileBytes);

        foyer.setImageUrl(blob);
        return foyerService.ajouterFoyerEtAffecterAUniversite(foyer,idUniversity);
    }
    @GetMapping("foyer/{universiteId}")
    public Foyer getFoyerByUniversite(@PathVariable Long universiteId) {
        Universite universite = iUniversiteService.retrieveUniversite(universiteId);

        return foyerService.getFoyerByUniv(universite);
    }
}
