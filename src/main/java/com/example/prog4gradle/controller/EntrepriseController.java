package com.example.prog4gradle.controller;


import com.example.prog4gradle.model.Entreprise;
import com.example.prog4gradle.model.EntrepriseModel;
import com.example.prog4gradle.service.EntrepriseService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;


@Controller
@AllArgsConstructor
public class EntrepriseController {

    private EntrepriseService entrepriseService;
    @GetMapping("/entreprise")
    public String showEntreprise(Model model, HttpSession session) {
        Entreprise entreprise = entrepriseService.getEntreprise(1);
        session.setAttribute("entreprise", entreprise);
        model.addAttribute("entreprise", entreprise);
        return "entreprise";
    }


    @GetMapping("/entreprise/edit")
    public String editEntrepriseForm(Model model) {
        Entreprise entreprise = entrepriseService.getEntreprise(1);
        model.addAttribute("entreprise", entreprise);
        return "entreprise-edit";
    }

    @PostMapping("/entreprise/edit")
    public String editEntrepriseSubmit(@ModelAttribute("entreprise") EntrepriseModel entrepriseModel, RedirectAttributes redirectAttributes) throws IOException {
        try {
            entrepriseService.updateEntreprise(entrepriseModel);
            return "redirect:/entreprise";
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("error", "Le numéro de téléphone est déjà utilisé.");
            return "redirect:/entreprise/edit";
        }
    }
}
