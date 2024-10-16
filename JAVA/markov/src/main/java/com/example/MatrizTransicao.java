package com.example;


import java.util.Random;

public class MatrizTransicao {

    public static void main(String[] args) {
        Random random = new Random();
        int mes = 30;
        double falha = 0.33;
        int custo = 0;
        int estadoFalhas = 0;
        int falhaLeve = 0;
        int falhaModerada = 0;
        int falhaSevera = 0;

        for (int i = 1; i <= mes; i++) {
            double chance = random.nextDouble(); // Gera um numero aleatorio entre 0 e 1
            if (chance < falha) {
                if (chance < 0.083) {
                    custo += 300;
                    estadoFalhas++;
                    falhaSevera++;
                    System.out.println("Falha severa no dia " + i + "! Custo acumulado: R$ " + custo);
                } else if (chance < 0.25) {
                    custo += 200;
                    estadoFalhas++;
                    falhaModerada++;
                    System.out.println("Falha moderada no dia " + i + "! Custo acumulado: R$ " + custo);
                } else if (chance < 0.50) {
                    custo += 100;
                    estadoFalhas++;
                    falhaLeve++;
                    System.out.println("Falha leve no dia " + i + "! Custo acumulado: R$ " + custo);
                } else {
                    // 50% de chance de não ocorrer falha
                    System.out.println("Dia " + i + " sem falhas.");
                }

                // Reseta o estado
                estadoFalhas = 0;
            } else {
                System.out.println("Dia " + i + " sem falhas.");
            }
            // Exibe o custo total no final do mês
            System.out.println("Custo total de manutenção ao longo de " + mes + " dias: R$ " + custo);
            System.out.println("Nível de falhas ao longo de " + mes + " dias: Falha Leve " + falhaLeve
                    + ", Falha Moderada: " + falhaModerada + ", Falha Severa: " + falhaSevera );
        }
    }
}
