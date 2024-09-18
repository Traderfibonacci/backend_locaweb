package br.com.fiap.localweb.dto;

import br.com.fiap.localweb.model.Theme;
import br.com.fiap.localweb.model.Category;
import lombok.Data;

@Data
public class UserPreferencesDto {
    private Theme theme;
    private String color;
    private Category category;
}

