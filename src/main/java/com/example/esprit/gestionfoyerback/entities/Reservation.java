package com.example.esprit.gestionfoyerback.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Reservation {
    @Id
    private  String idReservation ;
     private LocalDate anneeUnivirsitaire ;

     private boolean estValide ;
    @JsonIgnore

     @ManyToMany
     private List<Etudiant> etudiants ;



}
