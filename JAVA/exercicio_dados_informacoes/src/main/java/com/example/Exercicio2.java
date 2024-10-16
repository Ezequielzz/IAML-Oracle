package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Exercicio2 {
     public void teste() {
        try {
            // URL da API pública do GitHub para obter informações de um usuário
            URL url = new URL("http://localhost:3000/usuarios");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");


            // Verificar se a conexão foi bem-sucedida
            int status = con.getResponseCode();
            if (status != 200) {
                throw new RuntimeException("HTTP error code : " + status);
            }


            // Ler a resposta da API
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();


            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();

            //Processar o JSON recebido
            JSONArray usuarios = new JSONArray(content.toString());

            List<String> nomes = new ArrayList<>();
            int somaIdades = 0; 

            System.out.println("Dados dos Usuários:");

            for (int i = 0; i < usuarios.length(); i++) {


                JSONObject usuario = usuarios.getJSONObject(i);
                String nome = usuario.getString("nome");
                int idade = usuario.getInt("idade");
                String cidade = usuario.getString("cidade");

                nomes.add(nome);
                somaIdades += idade;

                System.out.println("Nome: " + nome + ", Idade: " + idade + ", Cidade: " + cidade);
            }

             // Número total de usuários
            int totalUsuarios = usuarios.length();
            // Média de idade dos usuários
            double mediaIdade = (double) somaIdades / totalUsuarios;

            // Imprimir o conteúdo da resposta
            System.out.println("\nNúmero total de usuários: " + totalUsuarios);
            System.out.println("Média de idade dos usuários: " + mediaIdade);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
