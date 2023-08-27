package com.example.prog4gradle.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@EqualsAndHashCode(exclude = "employee")
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "telephones")
public class Telephones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "telephones")
    private Set<Entreprise> entreprises = new HashSet<>();

    @ManyToMany(mappedBy = "telephones")
    private Set<Employee> employees = new HashSet<>();

    // Pour ajouter une entreprise associée à ce numéro de téléphone
    public void addEntreprise(Entreprise entreprise) {
        entreprises.add(entreprise);
        Set<Telephones> telephones = entreprise.getTelephones();
        if (telephones == null) {
            // Si c'est null, créer un nouvel ensemble pour les téléphones de l'employé
            telephones = new HashSet<>();
            entreprise.setTelephones(telephones);
        }

        // Associer le téléphone actuel à l'employé
        telephones.add(this);
    }

    // Pour ajouter un employé associé à ce numéro de téléphone
    public void addEmployee(Employee employee) {
        employees.add(employee);
        Set<Telephones> telephones = employee.getTelephones();
        if (telephones == null) {
            // Si c'est null, créer un nouvel ensemble pour les téléphones de l'employé
            telephones = new HashSet<>();
            employee.setTelephones(telephones);
        }

        // Associer le téléphone actuel à l'employé
        telephones.add(this);
    }


    public String numero;

    @Column(nullable = false)
    private String countryCode; // Champ pour stocker le code pays

    @Column(unique = true, nullable = false)
    private String phoneNumberIdentifier; // Champ pour stocker un identifiant unique du numéro de téléphone


    // Telephones class
    @Override
    public String toString() {
        return phoneNumberIdentifier;
    }

}

