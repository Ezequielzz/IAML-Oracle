package com.example.correcoes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.example.correcoes.Aluno;

public class LeituraNotas {

    public void app() {
        String inputFile = "alunos.txt";
        String outputFile = "alunos2.txt";

        List<Aluno> alunos = new ArrayList<>();
        double somaTotalNotas = 0;
        int totalAlunos = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                String nome = partes[0];
                double somaNotas = 0;
                int numeroNotas = 0;
                try {
                    for (int i = 1; i < partes.length; i++) {
                        somaNotas += Double.parseDouble(partes[i]);
                        numeroNotas++;
                    }
                } catch (NumberFormatException e) {
                    somaNotas += 0;
                }
                double media = somaNotas / numeroNotas; // Calcula a média do aluno
                alunos.add(new Aluno(nome, media)); // Adiciona o aluno à lista

                somaTotalNotas += media; // Soma a média do aluno à soma total
                totalAlunos++; // Incrementa o contador de alunos
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Aluno alunoMaiorNota = alunos.get(0);
        Aluno alunoMenorNota = alunos.get(0);

        for (Aluno aluno : alunos) {
            if (aluno.getMedia() > alunoMaiorNota.getMedia()) {
                alunoMaiorNota = aluno;
            }
            if (aluno.getMedia() < alunoMenorNota.getMedia()) {
                alunoMenorNota = aluno;
            }
        }

        double mediaGeralTurma = somaTotalNotas / totalAlunos;
        // Exibe os resultados no console
        System.out.println("");
        System.out.println(
                "Aluno com a maior nota: " + alunoMaiorNota.getNome() + " - Média: " + alunoMaiorNota.getMedia());
        System.out.println(
                "Aluno com a menor nota: " + alunoMenorNota.getNome() + " - Média: " + alunoMenorNota.getMedia());
        System.out.println("Média geral da turma: " + mediaGeralTurma);
    }
}