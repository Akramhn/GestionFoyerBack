package com.example.esprit.gestionfoyerback.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long idEtudiant ;
    private String nomEt ;
    private String prenomEt ;
    private long cin  ;
    private String ecole ;

    private Date dateNaissance ;

    @ManyToMany(mappedBy = "etudiants")
    private List<Reservation> reservations ;
}
