package com.example.correcoes;

import lombok.Getter;
import lombok.Setter;

public class Aluno {
    // atributos
    @Getter private String nome;
    @Getter private double media;

    public Aluno(String nome, double media) {
        this.nome = nome;
        this.media = media;
    }
}
