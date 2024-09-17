package br.com.fiap.localweb.dto;

import br.com.fiap.localweb.model.UserPreferences;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserAccountRegisterDto(
        Long id,
        @NotBlank(message = "O nome é obrigatório")
        String name,
        @NotBlank(message = "O email é obrigatório")
        @Email(message = "O formato do email está inválido")
        String email,
        @NotBlank(message = "A senha é obrigatório")
        String password,
        UserPreferences preferences
) {

}
