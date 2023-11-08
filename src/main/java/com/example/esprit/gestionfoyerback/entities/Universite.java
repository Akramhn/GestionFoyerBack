package com.example.esprit.gestionfoyerback.entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Universite {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Setter(AccessLevel.NONE)
 private long idUniversite ;
 private String nomUniversite ;
  private String adresse ;

  @OneToOne
  private Foyer foyer ;
}
