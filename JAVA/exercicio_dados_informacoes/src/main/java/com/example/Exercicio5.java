package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Exercicio5 {

    // ChatGPT foi usado para fazer este código

    // Método para exibir todos os clientes
    public void exibirClientes() {
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM clientes")) {

            System.out.println("Clientes cadastrados:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                System.out.println("ID: " + id + ", Nome: " + nome + ", Email: " + email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para adicionar um novo cliente
    public void adicionarCliente(String nome, String email) {
        String sql = "INSERT INTO clientes (nome, email) VALUES (?, ?)";
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, nome);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
            System.out.println("Cliente adicionado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar o e-mail de um cliente com base no ID
    public void atualizarEmailCliente(int id, String novoEmail) {
        String sql = "UPDATE clientes SET email = ? WHERE id = ?";
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, novoEmail);
            pstmt.setInt(2, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("E-mail do cliente atualizado com sucesso!");
            } else {
                System.out.println("Cliente com ID " + id + " não encontrado.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para excluir um cliente com base no ID
    public void excluirCliente(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cliente excluído com sucesso!");
            } else {
                System.out.println("Cliente com ID " + id + " não encontrado.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Exercicio5 app = new Exercicio5();

        // Exibindo todos os clientes
        app.exibirClientes();

        // Adicionando um novo cliente
        app.adicionarCliente("João da Silva", "joao.silva@gmail.com");

        // Atualizando o e-mail de um cliente com base no ID
        app.atualizarEmailCliente(1, "novo.email@exemplo.com");

        // Excluindo um cliente com base no ID
        app.excluirCliente(2);

        // Exibindo todos os clientes novamente após as operações
        app.exibirClientes();
    }
}
