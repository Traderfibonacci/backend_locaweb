package br.com.fiap.localweb.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_email")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipient;

    private String sender;

    private String subject;

    private String content;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "is_read")
    private Boolean isRead;
}
