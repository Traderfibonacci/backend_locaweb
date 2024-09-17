package br.com.fiap.localweb.model;

public enum Category {
    PESSOAL("pessoal"),
    TRABALHO("trabalho"),
    SOCIAL("social"),
    PROMOCAO("promocao");

    private String category;

    Category(String category){
        this.category = category;
    }

    public String getCategory(){
        return category;
    }
}
