package com.example.esprit.gestionfoyerback.contollers;

import com.example.esprit.gestionfoyerback.entities.Foyer;
import com.example.esprit.gestionfoyerback.entities.Universite;
import com.example.esprit.gestionfoyerback.repository.FoyerRepository;
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
    @Autowired
    private FoyerRepository foyerRepository;


    @GetMapping
    public List<Universite> retrieveAllUniversities(){
        return iUniversiteService.retrieveAllUniversities();
    }
    @PostMapping(consumes = "multipart/form-data")
    public Universite addUniversite( @RequestPart("nomUni") String nom,@RequestPart("adresse") String adr,@RequestPart("desc") String desc, @RequestPart("image") MultipartFile image) throws IOException, SQLException {
        Universite universite=new Universite();
        universite.setNomUniversite(nom);
        universite.setAdresse(adr);
        universite.setDescription(desc);
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


    @PutMapping(path = "{idUni}",consumes = "multipart/form-data")
    public Universite updateUniversite( @RequestPart("nomUni") String nom,@RequestPart("adresse") String adr,@RequestPart("desc") String desc, @RequestPart("image") MultipartFile image,@PathVariable long idUni) throws IOException, SQLException {
        Universite universite=new Universite();
        universite.setIdUniversite(idUni);
        universite.setNomUniversite(nom);
        universite.setAdresse(adr);
        universite.setDescription(desc);
        byte[] fileBytes = image.getBytes();

        // Convert the byte array to a Blob
        Blob blob = new SerialBlob(fileBytes);

        // Set the Blob in the Universite
        universite.setImageUrl(blob);

        // Save or process the Universite with the image
        return iUniversiteService.updateUniversite(universite);
    }
    @DeleteMapping("{idUniversite}")
    public void removeUniversite(@PathVariable long idUniversite){
        iUniversiteService.removeUniversite(idUniversite);
    }
}
