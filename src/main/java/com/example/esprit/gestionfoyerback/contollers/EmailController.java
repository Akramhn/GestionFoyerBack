package com.example.esprit.gestionfoyerback.contollers;


import com.example.esprit.gestionfoyerback.entities.EmailRequest;
import com.example.esprit.gestionfoyerback.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins = "*" )
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public String sendEmail(@RequestParam String to , @RequestParam String Subject ,@RequestParam String text) {
        try {
            emailService.sendEmail(to, Subject, text);
            return "Email sent successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error sending email: " + e.getMessage();
        }
    }
}

