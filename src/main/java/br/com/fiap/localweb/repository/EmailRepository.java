package br.com.fiap.localweb.repository;

import br.com.fiap.localweb.dto.EmailExhibitDto;
import br.com.fiap.localweb.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Long> {

    @Query("SELECT new br.com.fiap.localweb.dto.EmailExhibitDto(e) FROM Email e WHERE DATE(e.dateTime) BETWEEN :initialDate AND :finalDate")
    List<EmailExhibitDto> listEmailForPeriod(
            @Param("initialDate") LocalDate initialDate,
            @Param("finalDate") LocalDate finalDate
    );


    @Query("SELECT e FROM Email e WHERE e.recipient = :recipient AND e.isRead = false")
    List<EmailExhibitDto> listUnreadEmails(@Param("recipient") String recipient);

    @Query("SELECT e FROM Email e WHERE e.sender = :sender")
    List<EmailExhibitDto> listSentEmails(@Param("sender") String sender);

    @Query("SELECT e FROM Email e WHERE LOWER(e.subject) LIKE LOWER(CONCAT('%', :subject, '%'))")
    List<EmailExhibitDto>  searchEmailBySubject(@Param("subject") String subject);
}

