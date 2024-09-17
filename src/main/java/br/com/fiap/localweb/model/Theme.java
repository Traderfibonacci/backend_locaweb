package br.com.fiap.localweb.model;

public enum Theme {
    DARK("dark"),
    LIGHT("light");

    private String theme;

    Theme(String theme){
        this.theme = theme;
    }

    public String getTheme(){
        return theme;
    }
}
