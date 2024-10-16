package com.br.ezequielzz;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.br.ezequielzz.DiagnosticoSaudeID3.construirArvore;

public class Main {

    public static void main(String[] args) {
        No arvore = construirArvore();

        Scanner scanner = new Scanner(System.in);
        Map<String, String> sintomas = new HashMap<>();

        System.out.println("Você tem febre? (Sim/Não): ");
        sintomas.put("Febre", scanner.nextLine());

        System.out.println("Você tem tosse? (Sim/Não): ");
        sintomas.put("Tosse", scanner.nextLine());

        System.out.println("Você tem dor de cabeça? (Sim/Não): ");
        sintomas.put("Dor de Cabeça", scanner.nextLine());

        System.out.println("Você tem náusea? (Sim/Não): ");
        sintomas.put("Nausea", scanner.nextLine());

        System.out.println("Você tem dor de garganta? (Sim/Não): ");
        sintomas.put("Dor de Garganta", scanner.nextLine());

        System.out.println("Você está sentindo fadiga? (Sim/Não): ");
        sintomas.put("Fadiga", scanner.nextLine());

        String previsao = arvore.prever(sintomas);
        System.out.println("Possível doença: " + previsao);

        scanner.close();
    }
}
