package com.example.prog4gradle.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@ToString
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(columnDefinition="text")
    private String image;

    @Enumerated(EnumType.STRING)
    private Sexe sexe;

    @Column(columnDefinition="varchar(100)")
    private String numeroMatricule;



    @ManyToMany
    @JoinTable(
            name = "employee_telephones",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "telephone_id")
    )
    private Set<Telephones> telephones;


    private String adresse;

    @Column(name = "email_personnel")
    private String emailPersonnel;

    @Column(name = "email_professionnel")
    private String emailProfessionnel;

    // CIN
    @Column(name = "numero_cin")
    private String numeroCIN;

    @Column(name = "date_delivrance_cin")
    private LocalDate dateDelivranceCIN;

    @Column(name = "lieu_delivrance_cin")
    private String lieuDelivranceCIN;

    private String fonction;

    @Column(name = "nombre_enfants")
    private int nombreEnfants;

    @Column(name = "date_embauche")
    private LocalDate dateEmbauche;

    @Column(name = "date_depart")
    private LocalDate dateDepart;

    @Enumerated(EnumType.STRING)
    @Column(name = "categorie_socio_professionnelle")
    private CategorieSocioProfessionnelle categorieSocioProfessionnelle;

    @Column(columnDefinition="varchar(100)")
    private String CNAPS;

    public enum Sexe {
        M, // Masculin
        F  // Féminin
    }

    public enum CategorieSocioProfessionnelle {
        M1, M2, OS1, OS2, OS3, OP1A, OP1B, OP2A, OP2B, OP3
    }

    // Employee class
//    @Override
//    public String toString() {
//        return "Employee{" +
//                "id=" + id +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", dateOfBirth=" + dateOfBirth +
//                ", sexe=" + sexe +
//                ", numeroMatricule='" + numeroMatricule + '\'' +
//                ", telephones=" + (telephones != null ? telephones.toString() : "null") +
//                ", adresse='" + adresse + '\'' +
//                ", emailPersonnel='" + emailPersonnel + '\'' +
//                ", emailProfessionnel='" + emailProfessionnel + '\'' +
//                ", numeroCIN='" + numeroCIN + '\'' +
//                ", dateDelivranceCIN=" + dateDelivranceCIN +
//                ", lieuDelivranceCIN='" + lieuDelivranceCIN + '\'' +
//                ", fonction='" + fonction + '\'' +
//                ", nombreEnfants=" + nombreEnfants +
//                ", dateEmbauche=" + dateEmbauche +
//                ", dateDepart=" + dateDepart +
//                ", categorieSocioProfessionnelle=" + categorieSocioProfessionnelle +
//                ", CNAPS='" + CNAPS + '\'' +
//                '}';
//    }


}
