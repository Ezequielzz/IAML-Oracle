package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Exercicio3 {
    public void teste() {
        try {

            Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "postgres"
            );

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM produtos");

            // Variáveis para armazenar o maior e o menor preço dos produtos
            double maiorPreco = Double.MAX_VALUE;  // Inicia com o valor máximo possível, o que está incorreto (será corrigido adiante)
            double menorPreco = Double.MIN_VALUE;  // Inicia com o valor mínimo possível, o que está incorreto (será corrigido adiante)
            String produtoCaro = "";  // Armazena o nome do produto mais caro
            String produtoBarato = "";  // Armazena o nome do produto mais barato

            // Variáveis para calcular a soma dos preços e contar a quantidade de produtos
            double somaPrecos = 0;
            int quantProdutos = 0;

            System.out.println("Produtos Disponíveis:");
            
            // Loop para iterar por todos os registros (produtos) retornados pela consulta
            while (rs.next()) {
                // Obtendo o nome e o preço do produto no registro atual
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                
                // Exibindo o nome e o preço de cada produto
                System.out.println("Nome: " + nome + ", Preço: " + preco);

                // Atualizando as variáveis para encontrar o produto mais caro
                if (preco > maiorPreco) {
                    maiorPreco = preco;
                    produtoCaro = nome;
                }

                // Atualizando as variáveis para encontrar o produto mais barato
                if (preco > menorPreco) {
                    menorPreco = preco;
                    produtoBarato = nome;
                }

                // Somando os preços dos produtos e contando quantos produtos existem
                somaPrecos += preco;
                quantProdutos++;
            }

            // Calculando a média dos preços dos produtos
            double mediaPrecos = somaPrecos / quantProdutos;

            // Exibindo os resultados: produto mais caro, mais barato e a média de preços
            System.out.println("\nProduto mais caro: " + produtoCaro + " (Preço: " + maiorPreco + ")");
            System.out.println("Produto mais barato: " + produtoBarato + " (Preço: " + menorPreco + ")");
            System.out.println("Média de preços: " + mediaPrecos);

            // Fechando a conexão com o banco de dados
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
