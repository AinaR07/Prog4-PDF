package com.example.prog4gradle.controller;

import com.example.prog4gradle.model.Employee;
import com.example.prog4gradle.model.Entreprise;
import com.example.prog4gradle.service.EmployeeService;
import com.example.prog4gradle.service.EntrepriseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.thymeleaf.context.Context;
import java.io.ByteArrayOutputStream;



@Controller
@AllArgsConstructor
public class PdfController {

    private final EmployeeService employeeService;
    private EntrepriseService entrepriseService;
    private final SpringTemplateEngine templateEngine;

    @GetMapping("/generate-pdf/{id}")
    public ResponseEntity<byte[]> generatePdf(@PathVariable int id) throws Exception {

        Employee employee = employeeService.getEmployee(id);
        Entreprise entreprise = entrepriseService.getEntreprise(1);

        Context thymeleafContext = new Context();
        thymeleafContext.setVariable("employee", employee);
        thymeleafContext.setVariable("entreprise", entreprise);

        // Générez le HTML à partir du modèle Thymeleaf.
        String html = templateEngine.process("pdf-template", thymeleafContext);

        // Convertir le HTML en PDF en utilisant Flying Saucer.
        byte[] pdfBytes;
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(os);
            pdfBytes = os.toByteArray();
        }


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "Fiche d' employee.pdf");
        headers.setContentLength(pdfBytes.length);


        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }



}
