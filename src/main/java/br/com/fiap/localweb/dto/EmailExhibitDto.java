package br.com.fiap.localweb.dto;

import br.com.fiap.localweb.model.Email;

public record EmailExhibitDto(
        Long id,
        String recipient,
        String sender,
        String subject,
        String content,
        Boolean isRead
) {
    public EmailExhibitDto(Email email) {
       this(
               email.getId(),
               email.getRecipient(),
               email.getSender(),
               email.getSubject(),
               email.getContent(),
               email.getIsRead()
       );
    }
}
