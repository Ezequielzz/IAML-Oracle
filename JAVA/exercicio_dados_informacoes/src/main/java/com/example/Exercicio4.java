package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Exercicio4 {

    public void teste() {
        String arquivoCSV = "exercicio_dados_informacoes/src/main/java/com/example/dados.csv"; // Caminho do arquivo CSV
                                                                                               // de entrada
        String novoArquivoCSV = "dados_com_valor_total.csv"; // Caminho do novo arquivo CSV de saída
        String linha = "";
        String divisor = ","; // O divisor usado no CSV, neste caso é a vírgula

        List<String[]> linhas = new ArrayList<>(); // Lista para armazenar as linhas lidas
        double maiorValorEstoque = 0.0;
        String produtoMaiorValor = "";

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))) {
            // Lendo o cabeçalho (primeira linha) e armazenando
            String cabecalho = br.readLine();
            linhas.add(new String[] { cabecalho + ",valor_total_estoque" }); // Adicionando a nova coluna
                                                                             // "valor_total_estoque" no cabeçalho

            // Lendo o conteúdo do arquivo
            while ((linha = br.readLine()) != null) {
                String[] valores = linha.split(divisor);
                String nome = valores[0];
                int quantidade = Integer.parseInt(valores[1]);
                double preco = Double.parseDouble(valores[2]);

                // Calculando o valor total do estoque para o produto
                double valorTotalEstoque = quantidade * preco;

                // Verificando se este produto tem o maior valor total de estoque
                if (valorTotalEstoque > maiorValorEstoque) {
                    maiorValorEstoque = valorTotalEstoque;
                    produtoMaiorValor = nome;
                }

                // Adicionando os dados originais mais o valor total do estoque
                linhas.add(new String[] { nome, String.valueOf(quantidade), String.valueOf(preco),
                        String.valueOf(valorTotalEstoque) });
            }

            // Exibindo o produto com o maior valor total em estoque
            System.out.println("Produto com maior valor total em estoque: " + produtoMaiorValor + " (Valor: "
                    + maiorValorEstoque + ")");

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Gerando o novo arquivo CSV com a coluna adicional "valor_total_estoque"
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(novoArquivoCSV))) {
            for (String[] linhaCSV : linhas) {
                bw.write(String.join(",", linhaCSV));
                bw.newLine();
            }
            System.out.println("Novo arquivo CSV gerado com sucesso: " + novoArquivoCSV);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
