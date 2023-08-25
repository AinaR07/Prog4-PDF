package com.example.prog4gradle.controller;

import com.example.prog4gradle.model.Entreprise;
import com.example.prog4gradle.service.EntrepriseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@AllArgsConstructor
public class FooterController {

    private EntrepriseService entrepriseService;


    @ModelAttribute("entreprise")
    public Entreprise getEntrepriseInfo() {
        // Ici, vous pouvez appeler le service pour récupérer les informations de l'entreprise
        return entrepriseService.getEntreprise(1);
    }

}
