package br.com.fiap.localweb.service;

import br.com.fiap.localweb.dto.EmailExhibitDto;
import br.com.fiap.localweb.dto.EmailRegisterDto;
import br.com.fiap.localweb.exceptions.EmailLimitExceededException;
import br.com.fiap.localweb.model.Email;
import br.com.fiap.localweb.model.UserAccount;
import br.com.fiap.localweb.repository.EmailRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private EmailRepository repository;

    private static final int MAX_EMAILS_PER_HOUR = 10;
    private static final int MAX_EMAILS_PER_DAY = 100;
    private static final long MIN_INTERVAL_BETWEEN_EMAILS = 30000;

    private Map<String, UserEmailStats> userStats = new HashMap<>();

    public EmailExhibitDto sendEmail(EmailRegisterDto emailRegisterDto){
        String userId = emailRegisterDto.sender();
        if (canSendEmail(userId)) {
            Email email = new Email();
            email.setDateTime(LocalDateTime.now());
            BeanUtils.copyProperties(emailRegisterDto, email);
            return new EmailExhibitDto(repository.save(email));
        } else {
            throw new EmailLimitExceededException("Limite de envio de e-mails excedido. Tente novamente mais tarde.");
        }
    }

    private boolean canSendEmail(String userId) {
        UserEmailStats stats = userStats.getOrDefault(userId, new UserEmailStats());
        long currentTime = System.currentTimeMillis();

        if (stats.getEmailsSentInLastHour() >= MAX_EMAILS_PER_HOUR ||
                stats.getEmailsSentInLastDay() >= MAX_EMAILS_PER_DAY ||
                (currentTime - stats.getLastEmailSentTime()) < MIN_INTERVAL_BETWEEN_EMAILS) {
            return false;
        }

        stats.incrementEmailsSent(currentTime);
        userStats.put(userId, stats);
        return true;
    }

    @Data
    @NoArgsConstructor
    private static class UserEmailStats {
        private int emailsSentInLastHour;
        private int emailsSentInLastDay;
        private long lastEmailSentTime;

        public void incrementEmailsSent(long currentTime) {
            this.emailsSentInLastHour++;
            this.emailsSentInLastDay++;
            this.lastEmailSentTime = currentTime;
        }
    }


    public EmailExhibitDto findEmail(Long id){
        Optional<Email> emailOptional = repository.findById(id);
        if(emailOptional.isPresent()){
            return new EmailExhibitDto(emailOptional.get());
        }else{
            //Modificar o tipo da exceção
            throw new RuntimeException("Email não encontrado");
        }
    }

    public List<EmailExhibitDto> listAllEmails(){
        return repository.findAll().stream().map(EmailExhibitDto::new).toList();
    }

    public void deleteEmail(Long id){
        Optional<Email> emailOptional = repository.findById(id);
        if(emailOptional.isPresent()){
            Email email = emailOptional.get();
            repository.deleteById(email.getId());
        }else{
            throw new RuntimeException("Email não encontrado!");
        }

    }

    public void markAsRead(Long id){
        Optional<Email> emailOptional = repository.findById(id);
        if(emailOptional.isPresent()){
            Email email = emailOptional.get();
            email.setIsRead(true);
            repository.save(email);
        }else{
            throw new RuntimeException("Email não encontrado!");
        }
    }

    public List<EmailExhibitDto> listUnreadEmails(String recipient){
        return repository.listUnreadEmails(recipient);
    }

    public List<EmailExhibitDto> searchEmailBySubject(String subject){
        return repository.searchEmailBySubject(subject);
    }

    public List<EmailExhibitDto> listSentEmails(String sender){
        return repository.listSentEmails(sender);
    }


    public List<EmailExhibitDto> listEmailForPeriod(LocalDate initialDate, LocalDate finalDate) {
        return repository.listEmailForPeriod(initialDate, finalDate);
    }



    //Integrar com o calendário
    public void calendar(){}

}
