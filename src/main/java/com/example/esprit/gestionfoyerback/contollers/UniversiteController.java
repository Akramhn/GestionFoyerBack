package com.example.esprit.gestionfoyerback.contollers;

import com.example.esprit.gestionfoyerback.entities.Universite;
import com.example.esprit.gestionfoyerback.services.IUniversiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
@RestController
@RequestMapping("universite")
@CrossOrigin(origins = "*" )
public class UniversiteController {
    @Autowired
    private IUniversiteService iUniversiteService;


    @GetMapping
    public List<Universite> retrieveAllUniversities(){
        return iUniversiteService.retrieveAllUniversities();
    }
    @PostMapping(consumes = "multipart/form-data")
    public Universite addUniversite( @RequestParam("nomUni") String nom,@RequestParam("adresse") String adr, @RequestParam("image") MultipartFile image) throws IOException, SQLException {
        Universite universite=new Universite();
        universite.setNomUniversite(nom);
        universite.setAdresse(adr);
        byte[] fileBytes = image.getBytes();

        // Convert the byte array to a Blob
        Blob blob = new SerialBlob(fileBytes);

        // Set the Blob in the Universite
        universite.setImageUrl(blob);

        // Save or process the Universite with the image
        return iUniversiteService.addUniversite(universite);
    }
    @PutMapping
    public Universite updateUniversite(@RequestBody Universite u){
        return iUniversiteService.updateUniversite(u);
    }
    @GetMapping("{idUniversite}")
    public Universite retrieveUniversite(@PathVariable long idUniversite){
        return iUniversiteService.retrieveUniversite(idUniversite);
    }
    @PutMapping("{idFoyer}/{nomUniversite}")
    public Universite affecterFoyerAUniversite (@PathVariable long idFoyer,@PathVariable String nomUniversite){
        return iUniversiteService.affecterFoyerAUniversite(idFoyer,nomUniversite);
    }

    @PutMapping("{idUniversity}")
public Universite  desaffecterFoyerAUniversite(@PathVariable long idUniversity){
        return iUniversiteService.desaffecterFoyerAUniversite(idUniversity);
    }

}
