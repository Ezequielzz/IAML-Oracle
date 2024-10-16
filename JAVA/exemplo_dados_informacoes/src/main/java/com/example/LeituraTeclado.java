package com.example;

import java.util.Scanner;

public class LeituraTeclado {
    public void teste() {
        Scanner sc = new Scanner(System.in);

        // Leitura do nome
        System.out.println("Digite seu nome");
        String nome = sc.nextLine();

        // Leitura da Idade
        System.out.println("Digite sua idade");
        String idade = sc.nextLine();

        sc.close();

        System.out.println("Olá, " + nome + "! Sua idade é: " + idade);
    }
}
