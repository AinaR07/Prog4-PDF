package com.example.prog4gradle.service;


import com.example.prog4gradle.model.Entreprise;
import com.example.prog4gradle.model.EntrepriseModel;
import com.example.prog4gradle.model.Telephones;
import com.example.prog4gradle.repository.EntrepriseRepository;
import com.example.prog4gradle.repository.TelephonesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EntrepriseService {
    private EntrepriseRepository entrepriseRepository;
    private TelephonesRepository telephonesRepository;



    public  Entreprise getEntreprise(int id){
        Optional<Entreprise> optionalEntreprise = entrepriseRepository.findById(id);
        if (optionalEntreprise.isPresent()) {
            Entreprise entreprise = optionalEntreprise.get();
            return entreprise;
        } else {
            throw new NoSuchElementException("Entreprise is unique so make the id 1");
        }
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // Vérifier si le numéro de téléphone est composé de 10 chiffres uniquement
        return phoneNumber.matches("^\\d{10}$");
    }
    public Entreprise updateEntreprise(EntrepriseModel entrepriseModel) {
        Optional<Entreprise> optionalEntreprise = entrepriseRepository.findById(1);
        if (optionalEntreprise.isPresent()) {
            Entreprise entreprise = optionalEntreprise.get();
            entreprise.setNom(entrepriseModel.getNom());
            entreprise.setDescription(entrepriseModel.getDescription());
            entreprise.setSlogan(entrepriseModel.getSlogan());
            entreprise.setAdresse(entrepriseModel.getAdresse());
            entreprise.setEmailContact(entrepriseModel.getEmailContact());
            entreprise.setNif(entrepriseModel.getNif());
            entreprise.setStat(entrepriseModel.getStat());
            entreprise.setRcs(entrepriseModel.getRcs());

            if (entrepriseModel.getLogoFile() != null && !entrepriseModel.getLogoFile().isEmpty()) {
                try {
                    entreprise.setLogo(null);
                    MultipartFile logoFile = entrepriseModel.getLogoFile();
                    byte[] logoBytes = logoFile.getBytes();
                    String base64Logo = Base64.getEncoder().encodeToString(logoBytes);
                    entreprise.setLogo(base64Logo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Supprimer les anciens numéros de téléphone ici
            List<String> telephonesToDelete = entrepriseModel.getTelephonesToDelete();
            if (telephonesToDelete != null && !telephonesToDelete.isEmpty()) {
                List<String> filteredTelephonesToDelete = telephonesToDelete.stream()
                        .filter(numero -> numero != null && !numero.isEmpty())
                        .collect(Collectors.toList());

                for (String numero : filteredTelephonesToDelete) {
                    // Récupérer l'entité Telephones correspondant au numéro de téléphone
                    Telephones telephoneToDelete = telephonesRepository.findByPhoneNumberIdentifier(numero);
                    if (telephoneToDelete != null) {
                        // Supprimer le lien entre l'entité Entreprise et l'entité Telephones
                        entreprise.getTelephones().remove(telephoneToDelete);
                        telephoneToDelete.setEmployees(null);

                        // Supprimer l'entité Telephones de la base de données
                        telephonesRepository.delete(telephoneToDelete);
                    }
                }
            }


            // Ajouter les nouveaux numéros de téléphone ici
            if (entrepriseModel.getNewTelephones() != null && !entrepriseModel.getNewTelephones().isEmpty() && !entrepriseModel.getNewTelephones().isBlank()) {
                String[] newTelephoneNumbers = entrepriseModel.getNewTelephones().split(",");

                for (String newPhoneNumber : newTelephoneNumbers) {
                    // Create a new Telephones entity and set its properties
                    if (!isValidPhoneNumber(newPhoneNumber)) {
                        throw new IllegalArgumentException("Le numéro de téléphone n'est pas valide.");
                    }
                    Telephones newTelephone = new Telephones();
                    newTelephone.setNumero(newPhoneNumber.trim());
                    newTelephone.setCountryCode(entrepriseModel.getCountryCode());
                    newTelephone.setPhoneNumberIdentifier(entrepriseModel.getCountryCode() + " " + newPhoneNumber.trim());

                    // Save the new Telephones entity to the database
                    telephonesRepository.save(newTelephone);

                    // Associate the new Telephones entity with the Entreprise entity
                    entreprise.getTelephones().add(newTelephone);
                }
            }


            return entrepriseRepository.save(entreprise);
        } else {
            throw new NoSuchElementException("Entreprise not found for ID: 1");
        }
    }
}
