package com.example.esprit.gestionfoyerback.contollers;

import com.example.esprit.gestionfoyerback.entities.Etudiant;
import com.example.esprit.gestionfoyerback.repository.IEtudiantRepository;
import com.example.esprit.gestionfoyerback.services.IEtudiantServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/etudiant")
@RequiredArgsConstructor
public class EtudiantController {

    private final IEtudiantServices etudiantService;
    private  final IEtudiantRepository etudiantRepository;

    @PostMapping("/add")
    public Etudiant addEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.addEtudiant(etudiant);
    }

    @PutMapping("/update")
    public Etudiant updateEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.updateEtudiant(etudiant);
    }

    @GetMapping("/all")
    public List<Etudiant> getAllEtudiants() {
        return etudiantService.getAllEtudiants();
    }
    @GetMapping("/usersperpage")
    public Page<Etudiant> getAllStudent(@RequestParam(defaultValue = "0")  int page ,
                                        @RequestParam(defaultValue = "10")  int size
                                        ){
        Pageable pageable = PageRequest.of(page, size);
        return  etudiantService.getAllEtudiantsPage(pageable);

    }
    @GetMapping("/{idEtudiant}")
    public Etudiant getEtudiantById(@PathVariable Long idEtudiant) {
        return etudiantService.getEtudiantById(idEtudiant);
    }

    @DeleteMapping("/delete/{idEtudiant}")
    public void deleteEtudiant(@PathVariable Long idEtudiant) {
        etudiantService.deleteEtudiant(idEtudiant);
    }

    @PutMapping("/updatePassword/{idEtudiant}/{password}")
    public Etudiant updatePassword(@PathVariable Long idEtudiant, @PathVariable String  password) {
        return etudiantService.updatePassword(idEtudiant, password);
    }

    @PutMapping("/updateImage/{idEtudiant}")
    public ResponseEntity<?> updateImage(@PathVariable Long idEtudiant, @RequestParam("image") MultipartFile file) {
        try {
            Etudiant updatedEtudiant = etudiantService.updateImage(idEtudiant, file);
            return ResponseEntity.ok(updatedEtudiant);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating image: " + e.getMessage());
        }
    }
//    @GetMapping("/findByEmail/{email}")
//    public Etudiant searchEtudiantByEmail(@RequestParam String email) {
//
//        return  etudiantService.getEtudiantByEmail(email);
//
//    }
    @GetMapping("/findByEmail")
    public Etudiant searchEtudiantByEmail(@RequestParam String email) {
        return etudiantService.getEtudiantByEmail(email);
    }


}
