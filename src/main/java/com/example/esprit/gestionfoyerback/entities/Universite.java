package com.example.esprit.gestionfoyerback.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ext.SqlBlobSerializer;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Blob;

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
 @Lob
 @JsonSerialize(using = SqlBlobSerializer.class)
 private Blob imageUrl;
 @JsonBackReference
 @OneToOne(fetch = FetchType.EAGER) // par defaut exist
  private Foyer foyer ;
}
