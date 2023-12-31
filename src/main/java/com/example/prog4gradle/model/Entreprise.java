package com.example.prog4gradle.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "entreprise")
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private String slogan;
    private String adresse;

    @Column(name = "email_contact")
    private String emailContact;

    @ManyToMany
    @JoinTable(
            name = "entreprise_telephones",
            joinColumns = @JoinColumn(name = "entreprise_id"),
            inverseJoinColumns = @JoinColumn(name = "telephone_id")
    )
    private Set<Telephones> telephones;

    private String nif;
    private String stat;
    private String rcs;

    @Column(columnDefinition="text")
    private String logo;

    @Override
    public String toString() {
        return "Entreprise{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", slogan='" + slogan + '\'' +
                ", adresse='" + adresse + '\'' +
                ", emailContact='" + emailContact + '\'' +
                ", telephones=" + (telephones != null ? telephones.toString() : "null") +
                ", nif='" + nif + '\'' +
                ", stat='" + stat + '\'' +
                ", rcs='" + rcs + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }

}
