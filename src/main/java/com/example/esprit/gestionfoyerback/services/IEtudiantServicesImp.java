package com.example.esprit.gestionfoyerback.services;

import com.example.esprit.gestionfoyerback.entities.Etudiant;
import com.example.esprit.gestionfoyerback.entities.Role;
import com.example.esprit.gestionfoyerback.repository.IEtudiantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class IEtudiantServicesImp implements IEtudiantServices {

    public static String uploadDirectory = System.getProperty("user.dir") + "/uploadUser";

    private final IEtudiantRepository etudiantRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Etudiant addEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    @Override
    public Etudiant updateEtudiant(Etudiant etudiant) {
        if (etudiant.getId() != null) {
            Etudiant existingEtudiant = etudiantRepository.findById(etudiant.getId()).orElse(null);
            if (existingEtudiant != null) {
                if (etudiant.getNom() != null) {
                    existingEtudiant.setNom(etudiant.getNom());
                }
                if (etudiant.getPrenom() != null) {
                    existingEtudiant.setPrenom(etudiant.getPrenom());
                }
                if (etudiant.getImage() != null) {
                    existingEtudiant.setImage(etudiant.getImage());
                }
                if (etudiant.getRole() != null) {
                    existingEtudiant.setRole(etudiant.getRole());
                }
                if (etudiant.getEmail() != null) {
                    existingEtudiant.setEmail(etudiant.getEmail());
                }

                if (etudiant.getCin() != null) {
                    existingEtudiant.setCin(etudiant.getCin());
                }
                if (etudiant.getDateNaissance() != null) {
                    existingEtudiant.setDateNaissance(etudiant.getDateNaissance());
                }
                if (etudiant.getUniversite() != null) {

                    existingEtudiant.setUniversite(etudiant.getUniversite());
                }
                if (etudiant.getReservations() != null) {
                    existingEtudiant.setReservations(etudiant.getReservations());
                }
                return etudiantRepository.save(existingEtudiant);
            }
        }
        return null;
    }

    @Override
    public Etudiant updatePassword (Long idEtudiant, String password) {
        Etudiant etudiant = etudiantRepository.findById(idEtudiant).orElse(null);
        etudiant.setPassword(passwordEncoder.encode(password));
        return etudiantRepository.save(etudiant);
    }

    @Override
    public Etudiant updateImage (Long idEtudiant, MultipartFile file) {
        try {
            Etudiant etudiant = etudiantRepository.findById(idEtudiant).orElse(null);

            String originalFilename = file.getOriginalFilename();
            String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;
            Path fileNameAndPath = Paths.get(uploadDirectory, uniqueFilename);

            if (!Files.exists(fileNameAndPath.getParent())) {
                Files.createDirectories(fileNameAndPath.getParent());
            }

            Files.write(fileNameAndPath, file.getBytes());
            etudiant.setImage(uniqueFilename);
            return etudiantRepository.save(etudiant);
        } catch (IOException e) {
            throw new RuntimeException("Error processing file", e);
        }
    }

    @Override
    public Etudiant getEtudiantByEmail(String email) {
       return etudiantRepository.findByEmailContainsIgnoreCase(email);
    }
    @Override
    public Page<Etudiant> findAll(Pageable pageable) {
        return etudiantRepository.findAll(pageable);
    }

    @Override
    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant getEtudiantById(Long idEtudiant) {
        return etudiantRepository.findById(idEtudiant).orElse(null);
    }

    @Override
    public void deleteEtudiant(Long idEtudiant) {
        etudiantRepository.deleteById(idEtudiant);
    }


    @Override
    public Page<Etudiant> getAllEtudiantsPage(Pageable pageable) {
        return etudiantRepository.findAll(pageable);
    }

    @Override
    public Page<Etudiant> getAllEtudiantsByRole(Role role, Pageable pageable) {
        return etudiantRepository.findEtudiantsByRole(role, pageable);
    }

    @Override
    public Etudiant addAdmin(Etudiant etudiant) {
        etudiant.setRole(Role.ADMIN);
        return etudiantRepository.save(etudiant);
    }
}

