package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Exercicio1 {
    public void teste() {
        // Caminho do arquivo CSV
        String arquivoCSV = "exercicio_dados_informacoes/src/main/java/com/example/dados.txt";
        String linha = "";
        String divisor = ","; // Definindo o divisor usado no CSV (neste caso, vírgula)

        // Listas para armazenar os nomes dos alunos e as médias das suas notas
        List<String> nomes = new ArrayList<>();
        List<Double> medias = new ArrayList<>();

        // Bloco try para garantir o fechamento do arquivo após a leitura
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))) {

            // Lendo e imprimindo o conteúdo do arquivo linha por linha
            System.out.println("Conteúdo:");
            while ((linha = br.readLine()) != null) {
                // Dividindo a linha em partes (nome e notas)
                String[] valores = linha.split(divisor);
                String nome = valores[0]; // Nome do aluno
                double somaNotas = 0;
                int qtdNotas = valores.length - 1; // Quantidade de notas (excluindo o nome)

                // Calculando a soma das notas do aluno
                for (int i = 1; i < valores.length; i++) {
                    somaNotas += Double.parseDouble(valores[i]);
                }

                // Calculando a média das notas do aluno
                double media = somaNotas / qtdNotas;
                nomes.add(nome); // Adiciona o nome do aluno na lista
                medias.add(media); // Adiciona a média das notas na lista
            }

            // Variáveis para armazenar os dados do aluno com a maior e menor nota
            double maiorNota = Double.MIN_VALUE;
            double menorNota = Double.MAX_VALUE;
            String alunoMaiorNota = "";
            String alunoMenorNota = "";

            double somaGeral = 0; // Variável para calcular a média geral da turma

            // Iterando pelas médias calculadas
            for (int i = 0; i < medias.size(); i++) {
                double media = medias.get(i);
                somaGeral += media;

                // Verificando se a média atual é a maior
                if (media > maiorNota) {
                    maiorNota = media;
                    alunoMaiorNota = nomes.get(i); // Armazena o nome do aluno com a maior média
                }

                // Verificando se a média atual é a menor
                if (media < menorNota) {
                    menorNota = media;
                    alunoMenorNota = nomes.get(i); // Armazena o nome do aluno com a menor média
                }
            }

            // Calculando a média geral da turma
            double mediaGeral = somaGeral / medias.size();

            // Exibindo os resultados
            System.out.println("Aluno com a maior nota: " + alunoMaiorNota + " (Média: " + maiorNota + ")");
            System.out.println("Aluno com a menor nota: " + alunoMenorNota + " (Média: " + menorNota + ")");
            System.out.println("Média geral da turma: " + mediaGeral);

        } catch (IOException e) {
            // Tratamento de exceção em caso de erro na leitura do arquivo
            e.printStackTrace();
        }
    }
}
