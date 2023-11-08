package com.example.esprit.gestionfoyerback.entities;

import com.example.esprit.gestionfoyerback.enums.TypeChambre;
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
public class Chambre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long idChambre ;
    private long numeroChambre ;

    @Enumerated(EnumType.STRING)
    private TypeChambre typeC ;

    @ManyToOne
    private Bloc bloc ;

    @OneToMany
    private List<Reservation> reservations ;

}
