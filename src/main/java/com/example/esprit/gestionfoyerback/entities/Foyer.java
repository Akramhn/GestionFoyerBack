package com.example.esprit.gestionfoyerback.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Foyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long idFoyer ;
    private String nomFoyer ;
    private long capaciteFoyer ;

    @OneToMany (mappedBy = "foyer")
    private List<Bloc> blocs ;


    @OneToOne(mappedBy = "foyer")
    private Universite universite ;
}
