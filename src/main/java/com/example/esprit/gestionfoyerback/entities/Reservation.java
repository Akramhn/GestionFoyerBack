package com.example.esprit.gestionfoyerback.entities;

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
public class Reservation {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long idReservation ;
     private Date anneeUnivirsitaire ;

     private boolean estValide ;

     @ManyToMany
     private List<Etudiant> etudiants ;

     @ManyToOne
     private Chambre  chambre ;
}
