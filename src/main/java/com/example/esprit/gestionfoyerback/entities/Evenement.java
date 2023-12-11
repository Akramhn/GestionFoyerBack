package com.example.esprit.gestionfoyerback.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ext.SqlBlobSerializer;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idEvenement;

    String nomEvenement;
    String lieu;
    LocalDate dateEvenement;
    @Lob
    @JsonSerialize(using = SqlBlobSerializer.class)
    private Blob imageUrl;
    String description;
    @JsonIgnore
    @ManyToMany(mappedBy = "evenement",cascade = CascadeType.ALL)
    List<Participation> participations;

    // Constructors, getters, setters, and other annotations can be added as needed
}
