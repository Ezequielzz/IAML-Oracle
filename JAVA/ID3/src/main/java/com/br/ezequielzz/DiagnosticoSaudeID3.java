package com.br.ezequielzz;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Classe principal para o algoritmo ID3
public class DiagnosticoSaudeID3 {
    // Função para construir a árvore de decisão
    public static No construirArvore() {
        // Criação da árvore de decisão com base em sintomas
        No raiz = new No("Febre");

        // Nó com valor "Sim" para o sintoma Febre
        No febreSim = new No("Tosse");
        febreSim.adicionarFilho("Sim", new No("Dor de Cabeça", "Gripe"));
        febreSim.adicionarFilho("Não", new No("Dor de Cabeça", "Resfriado"));

        // Nó com valor "Não" para o sintoma Febre
        No febreNao = new No("Dor de Garganta");
        febreNao.adicionarFilho("Sim", new No("Fadiga", "Resfriado"));
        febreNao.adicionarFilho("Não", new No("Fadiga", "Nenhuma"));

        // Nó com valor "Sim" para o sintoma Dor de Cabeça (novo sintoma)
        No dorDeCabecaSim = new No("Nausea");
        dorDeCabecaSim.adicionarFilho("Sim", new No("Nausea", "Enxaqueca"));
        dorDeCabecaSim.adicionarFilho("Não", new No("Nausea", "Sinusite"));

        // Adiciona os nós filhos à raiz
        raiz.adicionarFilho("Sim", febreSim);
        raiz.adicionarFilho("Não", febreNao);

        return raiz;
    }
}
