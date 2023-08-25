package com.example.prog4gradle.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EntrepriseModel {
    private int id;
    private String nom;
    private String description;
    private String slogan;
    private String adresse;
    private String emailContact;
    private MultipartFile logoFile;
    private String countryCode;
    private String telephones;
    private List<String> telephonesToDelete;
    private String newTelephones;
    private String nif;
    private String stat;
    private String rcs;




}
