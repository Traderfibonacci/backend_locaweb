package br.com.fiap.localweb.dto;

import br.com.fiap.localweb.model.UserAccount;
import br.com.fiap.localweb.model.UserPreferences;

public record UserAccountExhibitDto(
        Long id,
        String name,
        String email,
        UserPreferences userPreferences
) {

    public UserAccountExhibitDto(UserAccount user){
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPreferences()
        );
    }

}
