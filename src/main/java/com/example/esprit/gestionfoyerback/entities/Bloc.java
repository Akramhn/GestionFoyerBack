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
public class Bloc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long idBloc ;
    private String nomBloc ;
    private long capaciteBloc ;

    @JsonBackReference
    @ManyToOne
    private Foyer foyer ;


    @OneToMany(mappedBy = "bloc")
    private List<Chambre> chambres ;

}
