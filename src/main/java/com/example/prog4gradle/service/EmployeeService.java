package com.example.prog4gradle.service;


import com.example.prog4gradle.model.Employee;
import com.example.prog4gradle.model.EmployeeModel;
import com.example.prog4gradle.model.Telephones;
import com.example.prog4gradle.repository.EmployeeRepository;
import com.example.prog4gradle.repository.TelephonesRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private TelephonesRepository telephonesRepository;
    @PersistenceContext
    private EntityManager entityManager;

    // Trier par nom en ordre croissant
    public List<Employee> findAllByOrderByLastNameAsc() {
        return entityManager.createNativeQuery("SELECT * FROM employee ORDER BY last_name ASC", Employee.class)
                .getResultList();
    }

    // Trier par nom en ordre décroissant
    public List<Employee> findAllByOrderByLastNameDesc() {
        return entityManager.createNativeQuery("SELECT * FROM employee ORDER BY last_name DESC", Employee.class)
                .getResultList();
    }

    // Trier par prénom en ordre croissant
    public List<Employee> findAllByOrderByFirstNameAsc() {
        return entityManager.createNativeQuery("SELECT * FROM employee ORDER BY first_name ASC", Employee.class)
                .getResultList();
    }

    // Trier par prénom en ordre décroissant
    public List<Employee> findAllByOrderByFirstNameDesc() {
        return entityManager.createNativeQuery("SELECT * FROM employee ORDER BY first_name DESC", Employee.class)
                .getResultList();
    }

    // Trier par sexe en ordre croissant
    public List<Employee> findAllByOrderBySexeAsc() {
        return entityManager.createNativeQuery("SELECT * FROM employee ORDER BY sexe ASC", Employee.class)
                .getResultList();
    }

    // Trier par sexe en ordre décroissant
    public List<Employee> findAllByOrderBySexeDesc() {
        return entityManager.createNativeQuery("SELECT * FROM employee ORDER BY sexe DESC", Employee.class)
                .getResultList();
    }

    // Trier par fonction en ordre croissant
    public List<Employee> findAllByOrderByFonctionAsc() {
        return entityManager.createNativeQuery("SELECT * FROM employee ORDER BY fonction ASC", Employee.class)
                .getResultList();
    }

    // Trier par fonction en ordre décroissant
    public List<Employee> findAllByOrderByFonctionDesc() {
        return entityManager.createNativeQuery("SELECT * FROM employee ORDER BY fonction DESC", Employee.class)
                .getResultList();
    }

    // Trier par date d'embauche en ordre croissant
    public List<Employee> findAllByOrderByDateEmbaucheAsc() {
        return entityManager.createNativeQuery("SELECT * FROM employee ORDER BY date_embauche ASC", Employee.class)
                .getResultList();
    }

    // Trier par date d'embauche en ordre décroissant
    public List<Employee> findAllByOrderByDateEmbaucheDesc() {
        return entityManager.createNativeQuery("SELECT * FROM employee ORDER BY date_embauche DESC", Employee.class)
                .getResultList();
    }

    // Trier par date de départ en ordre croissant
    public List<Employee> findAllByOrderByDateDepartAsc() {
        return entityManager.createNativeQuery("SELECT * FROM employee ORDER BY date_depart ASC", Employee.class)
                .getResultList();
    }

    // Trier par date de départ en ordre décroissant
    public List<Employee> findAllByOrderByDateDepartDesc() {
        return entityManager.createNativeQuery("SELECT * FROM employee ORDER BY date_depart DESC", Employee.class)
                .getResultList();
    }

    public Page<Employee> getAllEmployeesPaged(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll().stream().toList();
    }

    public List<Employee> searchEmployeesWithFilters(String firstName, String lastName, String sexe, String fonction) {

        firstName = (firstName != null && !firstName.isEmpty()) ? firstName : null;
        lastName = (lastName != null && !lastName.isEmpty()) ? lastName : null;
        sexe = (sexe != null && !sexe.isEmpty()) ? sexe : null;
        fonction = (fonction != null && !fonction.isEmpty()) ? fonction : null;

        return employeeRepository.findEmployeesByFilters(firstName, lastName, sexe, fonction);
    }

    public List<Employee> getAllEmployeesWithCountryCode(String countryCode) {
        return employeeRepository.findAllByCountryCode(countryCode);
    }


    public List<Employee> searchEmployeesByDateRange(LocalDate dateEmbaucheStart, LocalDate dateEmbaucheEnd,
                                                     LocalDate dateDepartStart, LocalDate dateDepartEnd) {
        if (dateEmbaucheStart != null && dateEmbaucheEnd != null) {
            return employeeRepository.findByDateEmbaucheBetween(dateEmbaucheStart, dateEmbaucheEnd);
        } else if (dateDepartStart != null && dateDepartEnd != null) {
            return employeeRepository.findByDateDepartBetween(dateDepartStart, dateDepartEnd);
        } else {
            return employeeRepository.findAll();
        }
    }


    public Employee getEmployee(int id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            return employee;
        } else {
            throw new NoSuchElementException("Employee not found for ID: " + id);
        }
    }


    public Employee addEmployee(EmployeeModel employeeModel) {
        Employee employee = new Employee();
        employee.setDateOfBirth(employeeModel.getDateOfBirth());
        employee.setFirstName(employeeModel.getFirstName());
        employee.setLastName(employeeModel.getLastName());
        employee.setNumeroMatricule(employeeModel.getNumeroMatricule());
        employee.setSexe(employeeModel.getSexe());
        employee.setAdresse(employeeModel.getAdresse());
        employee.setEmailPersonnel(employeeModel.getEmailPersonnel());
        employee.setEmailProfessionnel(employeeModel.getEmailProfessionnel());
        employee.setNumeroCIN(employeeModel.getNumeroCIN());
        employee.setDateDelivranceCIN(employeeModel.getDateDelivranceCIN());
        employee.setLieuDelivranceCIN(employeeModel.getLieuDelivranceCIN());
        employee.setFonction(employeeModel.getFonction());
        employee.setNombreEnfants(employeeModel.getNombreEnfants());
        employee.setDateEmbauche(employeeModel.getDateEmbauche());
        employee.setDateDepart(employeeModel.getDateDepart());
        employee.setCategorieSocioProfessionnelle(employeeModel.getCategorieSocioProfessionnelle());
        employee.setCNAPS(employeeModel.getCNAPS());

        if (employeeModel.getImageFile() != null && !employeeModel.getImageFile().isEmpty()) {
            try {
                MultipartFile imageFile = employeeModel.getImageFile();
                byte[] imageBytes = imageFile.getBytes();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                employee.setImage(base64Image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Save the employee in the database
        Employee savedEmployee = employeeRepository.save(employee);
        Optional<Employee> optionalEmployee = employeeRepository.findById(Math.toIntExact(savedEmployee.getId()));
        Set<Telephones> telephonesSet = new HashSet<>();
        if (optionalEmployee.isPresent()) {
            Employee employeeNew = optionalEmployee.get();
            employeeNew.setTelephones(telephonesSet);

            if (employeeModel.getTelephones() != null) {
                String[] newTelephoneNumbers = employeeModel.getTelephones().split(",");

                for (String newPhoneNumber : newTelephoneNumbers) {
                    // Create a new Telephones entity and set its properties
                    if (!isValidPhoneNumber(newPhoneNumber)) {
                        throw new IllegalArgumentException("Le numéro de téléphone n'est pas valide.");
                    }
                    Telephones newTelephone = new Telephones();
                    newTelephone.setNumero(newPhoneNumber.trim());
                    newTelephone.setCountryCode(employeeModel.getCountryCode());
                    newTelephone.setPhoneNumberIdentifier(employeeModel.getCountryCode() + " " + newPhoneNumber.trim());

                    // Save the new Telephones entity to the database
                    telephonesRepository.save(newTelephone);

                    // Associate the new Telephones entity with the Employee entity
                    employeeNew.getTelephones().add(newTelephone);
                }
            }
             employeeRepository.save(employeeNew);
        }
        return optionalEmployee.get();
    }


    private boolean isValidPhoneNumber(String phoneNumber) {
        // Vérifier si le numéro de téléphone est composé de 10 chiffres uniquement
        return phoneNumber.matches("^\\d{10}$");
    }



    public Employee updateEmployee(EmployeeModel employeeModel, int id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setDateOfBirth(employeeModel.getDateOfBirth());
            employee.setFirstName(employeeModel.getFirstName());
            employee.setLastName(employeeModel.getLastName());
            employee.setNumeroMatricule(employeeModel.getNumeroMatricule());

            //sexe
            Employee.Sexe newSexe = employeeModel.getSexe();
            if (newSexe != null) {
                employee.setSexe(newSexe);
            }

            // Supprimer les anciens numéros de téléphone ici
            List<String> telephonesToDelete = employeeModel.getTelephonesToDelete();
            if (telephonesToDelete != null && !telephonesToDelete.isEmpty()) {
                List<String> filteredTelephonesToDelete = telephonesToDelete.stream()
                        .filter(numero -> numero != null && !numero.isEmpty())
                        .collect(Collectors.toList());

                for (String numero : filteredTelephonesToDelete) {
                    // Récupérer l'entité Telephones correspondant au numéro de téléphone
                    Telephones telephoneToDelete = telephonesRepository.findByPhoneNumberIdentifier(numero);
                    if (telephoneToDelete != null) {
                        // Supprimer le lien entre l'entité Employee et l'entité Telephones
                        employee.getTelephones().remove(telephoneToDelete);
                        telephoneToDelete.setEmployees(null);

                        // Supprimer l'entité Telephones de la base de données
                        telephonesRepository.delete(telephoneToDelete);
                    }
                }
            }


            // Ajouter les nouveaux numéros de téléphone ici
            if (employeeModel.getNewTelephones() != null && !employeeModel.getNewTelephones().isEmpty() && !employeeModel.getNewTelephones().isBlank()) {
                String[] newTelephoneNumbers = employeeModel.getNewTelephones().split(",");

                for (String newPhoneNumber : newTelephoneNumbers) {
                    // Create a new Telephones entity and set its properties
                    if (!isValidPhoneNumber(newPhoneNumber)) {
                        throw new IllegalArgumentException("Le numéro de téléphone n'est pas valide.");
                    }
                    Telephones newTelephone = new Telephones();
                    newTelephone.setNumero(newPhoneNumber.trim());
                    newTelephone.setCountryCode(employeeModel.getCountryCode());
                    newTelephone.setPhoneNumberIdentifier(employeeModel.getCountryCode() + " " + newPhoneNumber.trim());

                    // Save the new Telephones entity to the database
                    telephonesRepository.save(newTelephone);

                    // Associate the new Telephones entity with the Employee entity
                    employee.getTelephones().add(newTelephone);
                }
            }

            employee.setAdresse(employeeModel.getAdresse());
            employee.setEmailPersonnel(employeeModel.getEmailPersonnel());
            employee.setEmailProfessionnel(employeeModel.getEmailProfessionnel());
            employee.setNumeroCIN(employeeModel.getNumeroCIN());
            employee.setDateDelivranceCIN(employeeModel.getDateDelivranceCIN());
            employee.setLieuDelivranceCIN(employeeModel.getLieuDelivranceCIN());
            employee.setFonction(employeeModel.getFonction());
            employee.setNombreEnfants(employeeModel.getNombreEnfants());
            employee.setDateEmbauche(employeeModel.getDateEmbauche());
            employee.setDateDepart(employeeModel.getDateDepart());
            employee.setCategorieSocioProfessionnelle(employeeModel.getCategorieSocioProfessionnelle());
            employee.setCNAPS(employeeModel.getCNAPS());

            if (employeeModel.getImageFile() != null && !employeeModel.getImageFile().isEmpty()) {
                try {
                    employee.setImage(null);
                    MultipartFile imageFile = employeeModel.getImageFile();
                    byte[] imageBytes = imageFile.getBytes();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    employee.setImage(base64Image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return employeeRepository.save(employee);
        } else {
            throw new NoSuchElementException("Employee not found for ID: " + id);
        }
    }


}


