package br.com.fiap.localweb.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Category {
    PESSOAL("pessoal"),
    TRABALHO("trabalho"),
    SOCIAL("social"),
    PROMOCAO("promocao");

    private final String category;

    Category(String category) {
        this.category = category;
    }

    @JsonValue
    public String getCategory() {
        return category;
    }

    @JsonCreator
    public static Category fromString(String category) {
        for (Category c : Category.values()) {
            if (c.category.equalsIgnoreCase(category)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Unknown category: " + category);
    }
}

