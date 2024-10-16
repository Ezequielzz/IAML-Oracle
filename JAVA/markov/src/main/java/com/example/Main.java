package com.example;

public class Main {
    public static void main(String[] args) {
        Markov cadeiaMarkov = new Markov(2);

        // Simula o tempo por 10 dias
        System.out.println("Previsão do tempo para os próximos 10 dias:");
        for (int i = 1; i <= 10; i++) {
            cadeiaMarkov.transicaoProximoDia();
            System.out.println("Dia " + i + ": " + cadeiaMarkov.obterEstadoAtual());
        }
    }
}