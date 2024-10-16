import org.junit.Before;
import org.junit.Test;

import com.example.Inventario;
import com.example.Produto;

import static org.junit.Assert.assertEquals;

import java.util.List;

public class InventarioTest {
    private Inventario inventario;

    @Before
    public void setup() {
        inventario = new Inventario();
    }

    @Test
    public void testeAdicionarProduto() {
        Produto produto = new Produto(1, "Produto A", 10, "Categoria A", 5.0);
        inventario.adicionarProduto(produto);
        List<Produto> produtos = inventario.listarProdutos();
        assertEquals(1, produtos.size());
    }

    @Test
    public void testeRemoverProdutoo() {
        testeAdicionarProduto();
        inventario.removerProduto(1);
        List<Produto> produtos = inventario.listarProdutos();
        assertEquals(0, produtos.size());
    }
    
    @Test
    public void testeAtualizarProduto() {
        testeAdicionarProduto();
        inventario.atualizarProduto(1, "Produto A", 40, "Categoria A", 6.0);
        Produto produtoAtualizado = inventario.listarProdutos().get(0);
        assertEquals("Produto A", produtoAtualizado.getNome());
        assertEquals(40, produtoAtualizado.getQuantidade());
        assertEquals(6.0, produtoAtualizado.getPreco(), 0.001);
    }

}
