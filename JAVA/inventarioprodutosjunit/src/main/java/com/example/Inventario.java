package com.example;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Produto> produtos;

    public Inventario() {
        produtos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void removerProduto(int id) {
        produtos.removeIf(produto -> produto.getId() == id);
    }

    public void atualizarProduto(int id, String nome, int quantidade, String categoria, double preco) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                produto.setNome(nome);
                produto.setQuantidade(quantidade);
                produto.setCategoria(categoria);
                produto.setPreco(preco);
                break;
            }
        }
    }

    public List<Produto> listarProdutos() {
        return new ArrayList<>(produtos);
    }
}
