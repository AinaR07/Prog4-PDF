package com.example.prog4gradle.controller;
import com.example.prog4gradle.model.Employee;
import com.example.prog4gradle.model.EmployeeModel;
import com.example.prog4gradle.model.Entreprise;
import com.example.prog4gradle.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;



    @GetMapping("/employees-nom-asc")
    public String getAllEmployeesOrderedByLastName(Model model, HttpSession session) {

        // Récupérer l'objet Entreprise depuis la session
        Entreprise entreprise = (Entreprise) session.getAttribute("entreprise");

        // Utiliser l'objet Entreprise dans le contrôleur
        if (entreprise != null) {
            // Faites quelque chose avec l'objet Entreprise, par exemple : entreprise.getNom()
            model.addAttribute("entreprise", entreprise);
        }

        List<Employee> employees = employeeService.findAllByOrderByLastNameAsc();
        session.setAttribute("employeesToExport", employees);
        model.addAttribute("employees", employees);
        return "employees";
    }
    @GetMapping("/employees-nom-desc")
    public String getAllEmployeesOrderedByLastNameDesc(Model model, HttpSession session) {

        // Récupérer l'objet Entreprise depuis la session
        Entreprise entreprise = (Entreprise) session.getAttribute("entreprise");

        // Utiliser l'objet Entreprise dans le contrôleur
        if (entreprise != null) {
            // Faites quelque chose avec l'objet Entreprise, par exemple : entreprise.getNom()
            model.addAttribute("entreprise", entreprise);
        }

        List<Employee> employees = employeeService.findAllByOrderByLastNameDesc();
        session.setAttribute("employeesToExport", employees);
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/employees-prenom-asc")
    public String getAllEmployeesOrderedByFirstName(Model model,HttpSession session) {

        // Récupérer l'objet Entreprise depuis la session
        Entreprise entreprise = (Entreprise) session.getAttribute("entreprise");

        // Utiliser l'objet Entreprise dans le contrôleur
        if (entreprise != null) {
            // Faites quelque chose avec l'objet Entreprise, par exemple : entreprise.getNom()
            model.addAttribute("entreprise", entreprise);
        }

        List<Employee> employees = employeeService.findAllByOrderByFirstNameAsc();
        session.setAttribute("employeesToExport", employees);
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/employees-prenom-desc")
    public String getAllEmployeesOrderedByFirstNameDesc(Model model,HttpSession session) {

        // Récupérer l'objet Entreprise depuis la session
        Entreprise entreprise = (Entreprise) session.getAttribute("entreprise");

        // Utiliser l'objet Entreprise dans le contrôleur
        if (entreprise != null) {
            // Faites quelque chose avec l'objet Entreprise, par exemple : entreprise.getNom()
            model.addAttribute("entreprise", entreprise);
        }

        List<Employee> employees = employeeService.findAllByOrderByFirstNameDesc();
        session.setAttribute("employeesToExport", employees);
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/employees-sexe-asc")
    public String getAllEmployeesOrderedBySexe(Model model,HttpSession session) {

        // Récupérer l'objet Entreprise depuis la session
        Entreprise entreprise = (Entreprise) session.getAttribute("entreprise");

        // Utiliser l'objet Entreprise dans le contrôleur
        if (entreprise != null) {
            // Faites quelque chose avec l'objet Entreprise, par exemple : entreprise.getNom()
            model.addAttribute("entreprise", entreprise);
        }

        List<Employee> employees = employeeService.findAllByOrderBySexeAsc();
        session.setAttribute("employeesToExport", employees);
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/employees-sexe-desc")
    public String getAllEmployeesOrderedBySexeDesc(Model model,HttpSession session) {

        // Récupérer l'objet Entreprise depuis la session
        Entreprise entreprise = (Entreprise) session.getAttribute("entreprise");

        // Utiliser l'objet Entreprise dans le contrôleur
        if (entreprise != null) {
            // Faites quelque chose avec l'objet Entreprise, par exemple : entreprise.getNom()
            model.addAttribute("entreprise", entreprise);
        }

        List<Employee> employees = employeeService.findAllByOrderBySexeDesc();
        session.setAttribute("employeesToExport", employees);
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/employees-fonction-asc")
    public String getAllEmployeesOrderedByFonction(Model model,HttpSession session) {

        // Récupérer l'objet Entreprise depuis la session
        Entreprise entreprise = (Entreprise) session.getAttribute("entreprise");

        // Utiliser l'objet Entreprise dans le contrôleur
        if (entreprise != null) {
            // Faites quelque chose avec l'objet Entreprise, par exemple : entreprise.getNom()
            model.addAttribute("entreprise", entreprise);
        }

        List<Employee> employees = employeeService.findAllByOrderByFonctionAsc();
        session.setAttribute("employeesToExport", employees);
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/employees-fonction-desc")
    public String getAllEmployeesOrderedByFonctionDesc(Model model,HttpSession session) {

        // Récupérer l'objet Entreprise depuis la session
        Entreprise entreprise = (Entreprise) session.getAttribute("entreprise");

        // Utiliser l'objet Entreprise dans le contrôleur
        if (entreprise != null) {
            // Faites quelque chose avec l'objet Entreprise, par exemple : entreprise.getNom()
            model.addAttribute("entreprise", entreprise);
        }

        List<Employee> employees = employeeService.findAllByOrderByFonctionDesc();
        session.setAttribute("employeesToExport", employees);
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/employees-date-embauche-asc")
    public String getAllEmployeesOrderedByDateEmbauche(Model model,HttpSession session) {

        // Récupérer l'objet Entreprise depuis la session
        Entreprise entreprise = (Entreprise) session.getAttribute("entreprise");

        // Utiliser l'objet Entreprise dans le contrôleur
        if (entreprise != null) {
            // Faites quelque chose avec l'objet Entreprise, par exemple : entreprise.getNom()
            model.addAttribute("entreprise", entreprise);
        }

        List<Employee> employees = employeeService.findAllByOrderByDateEmbaucheAsc();
        session.setAttribute("employeesToExport", employees);
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/employees-date-embauche-desc")
    public String getAllEmployeesOrderedByDateEmbaucheDesk(Model model,HttpSession session) {

        // Récupérer l'objet Entreprise depuis la session
        Entreprise entreprise = (Entreprise) session.getAttribute("entreprise");

        // Utiliser l'objet Entreprise dans le contrôleur
        if (entreprise != null) {
            // Faites quelque chose avec l'objet Entreprise, par exemple : entreprise.getNom()
            model.addAttribute("entreprise", entreprise);
        }

        List<Employee> employees = employeeService.findAllByOrderByDateEmbaucheDesc();
        session.setAttribute("employeesToExport", employees);
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/employees-date-depart-asc")
    public String getAllEmployeesOrderedByDateDepart(Model model,HttpSession session) {

        // Récupérer l'objet Entreprise depuis la session
        Entreprise entreprise = (Entreprise) session.getAttribute("entreprise");

        // Utiliser l'objet Entreprise dans le contrôleur
        if (entreprise != null) {
            // Faites quelque chose avec l'objet Entreprise, par exemple : entreprise.getNom()
            model.addAttribute("entreprise", entreprise);
        }

        List<Employee> employees = employeeService.findAllByOrderByDateDepartAsc();
        session.setAttribute("employeesToExport", employees);
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/employees-date-depart-desc")
    public String getAllEmployeesOrderedByDateDepartDesc(Model model,HttpSession session) {

        // Récupérer l'objet Entreprise depuis la session
        Entreprise entreprise = (Entreprise) session.getAttribute("entreprise");

        // Utiliser l'objet Entreprise dans le contrôleur
        if (entreprise != null) {
            // Faites quelque chose avec l'objet Entreprise, par exemple : entreprise.getNom()
            model.addAttribute("entreprise", entreprise);
        }

        List<Employee> employees = employeeService.findAllByOrderByDateDepartDesc();
        session.setAttribute("employeesToExport", employees);
        model.addAttribute("employees", employees);
        return "employees";
    }



    @GetMapping("/employees")
    public String getEmployeesFiltered(@RequestParam(value = "firstName", required = false) String firstName,
                                       @RequestParam(value = "lastName", required = false) String lastName,
                                       @RequestParam(value = "sexe", required = false) String sexe,
                                       @RequestParam(value = "fonction", required = false) String fonction,
                                       Model model, HttpSession session) {


        // Récupérer l'objet Entreprise depuis la session
        Entreprise entreprise = (Entreprise) session.getAttribute("entreprise");

        // Utiliser l'objet Entreprise dans le contrôleur
        if (entreprise != null) {
            // Faites quelque chose avec l'objet Entreprise, par exemple : entreprise.getNom()
            model.addAttribute("entreprise", entreprise);
        }

        List<Employee> employees;

        if ( (firstName != null && !firstName.isEmpty()) || (lastName != null && !lastName.isEmpty()) || (sexe != null && !sexe.isEmpty())
                || (fonction != null && !fonction.isEmpty())) {
            employees = employeeService.searchEmployeesWithFilters(firstName, lastName, sexe, fonction);
        } else {
            employees = employeeService.getAllEmployees();
        }

        // Stocker les employés dans la session pour l'export CSV
        session.setAttribute("employeesToExport", employees);
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/employees-code")
    public String getEmployeesByPhones(@RequestParam(value = "countryCode", required = false) String countryCode,
                                       Model model, HttpSession session) {

        // Récupérer l'objet Entreprise depuis la session
        Entreprise entreprise = (Entreprise) session.getAttribute("entreprise");

        // Utiliser l'objet Entreprise dans le contrôleur
        if (entreprise != null) {
            // Faites quelque chose avec l'objet Entreprise, par exemple : entreprise.getNom()
            model.addAttribute("entreprise", entreprise);
        }

        List<Employee> employees;

        if (countryCode != null && !countryCode.isEmpty() && !countryCode.isBlank()) {
            employees = employeeService.getAllEmployeesWithCountryCode(countryCode);
        } else {
            employees = employeeService.getAllEmployees();
        }

        // Stocker les employés dans la session pour l'export CSV
        session.setAttribute("employeesToExport", employees);
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/employees-date-filtered")
    public String searchEmployeesByDateRange(
            @RequestParam(name = "dateEmbaucheStart", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEmbaucheStart,
            @RequestParam(name = "dateEmbaucheEnd", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEmbaucheEnd,
            @RequestParam(name = "dateDepartStart", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDepartStart,
            @RequestParam(name = "dateDepartEnd", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDepartEnd,
            Model model, HttpSession session) {

        // Récupérer l'objet Entreprise depuis la session
        Entreprise entreprise = (Entreprise) session.getAttribute("entreprise");

        // Utiliser l'objet Entreprise dans le contrôleur
        if (entreprise != null) {
            // Faites quelque chose avec l'objet Entreprise, par exemple : entreprise.getNom()
            model.addAttribute("entreprise", entreprise);
        }

        List<Employee> employees = employeeService.searchEmployeesByDateRange(
                dateEmbaucheStart, dateEmbaucheEnd, dateDepartStart, dateDepartEnd);

        // Stocker les employés dans la session pour l'export CSV
        session.setAttribute("employeesToExport", employees);
        model.addAttribute("employees", employees);
        return "employees";
    }


    @GetMapping("/add-employee")
    public String addEmployeeForm(Model model, HttpSession session) {

        // Récupérer l'objet Entreprise depuis la session
        Entreprise entreprise = (Entreprise) session.getAttribute("entreprise");

        // Utiliser l'objet Entreprise dans le contrôleur
        if (entreprise != null) {
            // Faites quelque chose avec l'objet Entreprise, par exemple : entreprise.getNom()
            model.addAttribute("entreprise", entreprise);
        }

        model.addAttribute("employee", new EmployeeModel());
        return "add-employee";
    }

    @PostMapping("/add-employee")
    public String addEmployee(@ModelAttribute("employee") EmployeeModel employeeModel, RedirectAttributes redirectAttributes, HttpSession session) throws IOException {
        try {
            employeeService.addEmployee(employeeModel);
            return "redirect:/employees";
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("error", "Le numéro de téléphone est déjà utilisé.");
            return "redirect:/add-employee";
        }
    }
    @GetMapping("/fiche/{id}")
    public String getEmployee(@PathVariable int id, Model model, HttpSession session) {

        // Récupérer l'objet Entreprise depuis la session
        Entreprise entreprise = (Entreprise) session.getAttribute("entreprise");

        // Utiliser l'objet Entreprise dans le contrôleur
        if (entreprise != null) {
            // Faites quelque chose avec l'objet Entreprise, par exemple : entreprise.getNom()
            model.addAttribute("entreprise", entreprise);
        }

        Employee employee = employeeService.getEmployee(id);
        model.addAttribute("employee", employee);
        return "employee-details";
    }



    @GetMapping("/fiche/{id}/edit")
    public String getEmployeeEdit(@PathVariable int id, Model model) {
        Employee employee = employeeService.getEmployee(id);
        if (employee.getSexe() == null || (!employee.getSexe().equals(Employee.Sexe.M) && !employee.getSexe().equals(Employee.Sexe.F))) {
            employee.setSexe(Employee.Sexe.M); //
        }
        model.addAttribute("employee", employee);
        return "employee-edit";
    }


    @PostMapping("/fiche/{id}/edit")
    public String updateEmployee(@ModelAttribute("employee") EmployeeModel employeeModel, @PathVariable int id, RedirectAttributes redirectAttributes) throws IOException {
        try {
            employeeService.updateEmployee(employeeModel, id);
            return "redirect:/fiche/" + id;
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("error", "Le numéro de téléphone est déjà utilisé.");
            return "redirect:/fiche/" + id + "/edit";
        }
    }


    @GetMapping("/employees/export")
    public void exportEmployeesToCSV(HttpServletResponse response, HttpSession session) throws IOException {
        // Récupérer les employés à partir de la session
        List<Employee> employees = (List<Employee>) session.getAttribute("employeesToExport");

        // Vérifier que la liste n'est pas vide
        if (employees == null || employees.isEmpty()) {
            response.setContentType("text/plain");
            response.getWriter().write("Aucun employé à exporter.");
            return;
        }

        // Configurer le content type pour indiquer un fichier CSV
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"employees.csv\"");

        // Utiliser un PrintWriter pour écrire les données CSV dans la réponse
        try (PrintWriter writer = response.getWriter()) {
            // Écrire l'en-tête du CSV
            writer.println("Prénom;Nom;Date d'embauche;Date de départ;Sexe;Fonction");

            // Écrire chaque employé au format CSV
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            for (Employee employee : employees) {
                writer.println(employee.getFirstName() + ";" +
                        employee.getLastName() + ";" +
                        (employee.getDateEmbauche() != null ? employee.getDateEmbauche().format(dateFormatter) : "") + ";" +
                        (employee.getDateDepart() != null ? employee.getDateDepart().format(dateFormatter) : "") + ";" +
                        employee.getSexe() + ";" +
                        employee.getFonction());
            }
        }
    }

}

