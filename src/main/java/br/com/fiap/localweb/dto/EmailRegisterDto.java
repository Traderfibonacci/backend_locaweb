package br.com.fiap.localweb.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmailRegisterDto(
        @NotBlank(message = "O destinatário é obrigatório")
        @Email(message = "O formato do email está inválido")
        String recipient,
        @NotBlank(message = "O remetente é obrigatório")
        @Email(message = "O formato do email está inválido")
        String sender,
        @NotBlank(message = "O assunto é obrigatório")
        String subject,
        @NotBlank(message = "O conteúdo é obrigatório")
        String content,
        @NotNull(message = "O campo isRead é obrigatório")
        Boolean isRead
) {
}
