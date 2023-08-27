package com.example.prog4gradle.model;

import lombok.*;
import java.time.LocalDate;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class
EmployeeModel {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private MultipartFile imageFile;
    private String numeroMatricule;
    private Employee.Sexe sexe;
    private String telephones;
    private List<String> telephonesToDelete;
    private String newTelephones;
    private String countryCode;
    private String adresse;
    private String emailPersonnel;
    private String emailProfessionnel;
    private String numeroCIN;
    private LocalDate dateDelivranceCIN;
    private String lieuDelivranceCIN;
    private String fonction;
    private int nombreEnfants;
    private LocalDate dateEmbauche;
    private LocalDate dateDepart;
    private Employee.CategorieSocioProfessionnelle categorieSocioProfessionnelle;
    private String CNAPS;
    private int age;
    private long salaireBrut;

}
