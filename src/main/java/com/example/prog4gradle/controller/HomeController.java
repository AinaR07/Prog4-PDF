package com.example.prog4gradle.controller;

import com.example.prog4gradle.model.Entreprise;
import com.example.prog4gradle.service.EntrepriseService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class HomeController {
    private EntrepriseService entrepriseService;
    @GetMapping("/")
    public String homePage(Model model, HttpSession session) {
        Entreprise entreprise = entrepriseService.getEntreprise(1);
        session.setAttribute("entreprise", entreprise);
        model.addAttribute("entreprise", entreprise);
        return "index";
    }
}
