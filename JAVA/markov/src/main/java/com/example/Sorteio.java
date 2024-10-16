package com.example;

import java.util.*;

public class Sorteio {
    public static void main(String[] args) {
        List<String> bolas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            bolas.add("Azul");
            bolas.add("Vermelha");
        }

        Random random = new Random();
        int sorteios = 0;

        // Sortear enquanto houver uma bola azul
        while (bolas.contains("Azul")) {
            sorteios++;

            int indiceSorteado = random.nextInt(bolas.size());
            String bolaSorteada = bolas.get(indiceSorteado);

            System.out.println("Sorteio " + sorteios + ": Bola sorteada - " + bolaSorteada);

            if (bolaSorteada.equals("Azul")) {
                bolas.remove(indiceSorteado);
                System.out.println("Bola azul removida!");
            }
            // Se for uma bola vermelha, ela continua no sorteio
        }
        // Exibir o número total de sorteios
        System.out.println("Todas as bolas azuis foram removidas em " + sorteios + " sorteios.");
    }
}
