package com.example.esprit.gestionfoyerback.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ext.SqlBlobSerializer;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Blob;
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
    @Lob
    @JsonSerialize(using = SqlBlobSerializer.class)
    private Blob imageUrl;
    @JsonBackReference
    @OneToMany (mappedBy = "foyer" , cascade = CascadeType.ALL)
    private List<Bloc> blocs ;

    @OneToOne(mappedBy = "foyer" )
    private Universite universite ;
}
