package com.example.esprit.gestionfoyerback.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idParticipation;

    LocalDate dateParticipation;
    String nomEvenement;
    @ManyToOne
    private Etudiant etudiant ;

    @ManyToMany
    private List<Evenement> evenement;

}
