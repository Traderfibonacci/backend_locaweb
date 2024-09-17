package br.com.fiap.localweb.controller;

import br.com.fiap.localweb.dto.EmailExhibitDto;
import br.com.fiap.localweb.dto.EmailRegisterDto;
import br.com.fiap.localweb.exceptions.EmailLimitExceededException;
import br.com.fiap.localweb.model.Email;
import br.com.fiap.localweb.model.UserAccount;
import br.com.fiap.localweb.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<?> sendEmail(@RequestBody @Valid EmailRegisterDto email) {
        try {
            EmailExhibitDto response = emailService.sendEmail(email);
            return ResponseEntity.ok(response);
        } catch (EmailLimitExceededException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(errorResponse);
        }
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmailExhibitDto findEmail(@PathVariable Long id){
        return emailService.findEmail(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmailExhibitDto> listAllEmails(){
        return emailService.listAllEmails();
    }

    @DeleteMapping("id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmail(@PathVariable Long id){
        emailService.deleteEmail(id);
    }

    @PutMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void markAsRead(@PathVariable Long id){
        emailService.markAsRead(id);
    }

    @GetMapping("/recipient/{recipient}")
    @ResponseStatus(HttpStatus.OK)
    public List<EmailExhibitDto> listUnreadEmails(@PathVariable String recipient){
        return emailService.listUnreadEmails(recipient);
    }

    @GetMapping("/subject/{subject}")
    @ResponseStatus(HttpStatus.OK)
    public List<EmailExhibitDto> searchEmailBySubject(@PathVariable String subject){
        return emailService.searchEmailBySubject(subject);
    }

    @GetMapping("/sender/{sender}")
    @ResponseStatus(HttpStatus.OK)
    public List<EmailExhibitDto> listSentEmails(@PathVariable String sender){
        return emailService.listSentEmails(sender);
    }

    @GetMapping(value = "/date", params = {"initialDate", "finalDate"})
    @ResponseStatus(HttpStatus.OK)
    public List<EmailExhibitDto> listEmailForPeriod(
            @RequestParam LocalDate initialDate,
            @RequestParam LocalDate finalDate){
        return emailService.listEmailForPeriod(initialDate, finalDate);
    }

}
