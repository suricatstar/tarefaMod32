package main.java.br.com.ebac;

import main.java.br.com.ebac.dao.IProdutoDAO;
import main.java.br.com.ebac.dao.ProdutoDAO;
import main.java.br.com.ebac.model.Produto;
import main.java.br.com.ebac.util.JPAUtil;

import java.math.BigDecimal;

public class App {

    public static void main(String[] args) {
        IProdutoDAO produtoDAO = new ProdutoDAO();

        try {
            // Cadastrar produtos
            System.out.println("=== CADASTRANDO PRODUTOS ===");
            Produto p1 = new Produto("PROD001", "Notebook Dell", new BigDecimal("3500.00"));
            p1.setDescricao("Notebook Dell Inspiron 15");
            p1.setQuantidadeEstoque(10);
            produtoDAO.cadastrar(p1);
            System.out.println("Produto cadastrado: " + p1);

            Produto p2 = new Produto("PROD002", "Mouse Logitech", new BigDecimal("80.00"));
            p2.setDescricao("Mouse sem fio");
            p2.setQuantidadeEstoque(50);
            produtoDAO.cadastrar(p2);
            System.out.println("Produto cadastrado: " + p2);

            Produto p3 = new Produto("PROD003", "Teclado Mecânico", new BigDecimal("350.00"));
            p3.setDescricao("Teclado mecânico RGB");
            p3.setQuantidadeEstoque(25);
            produtoDAO.cadastrar(p3);
            System.out.println("Produto cadastrado: " + p3);

            // Buscar todos
            System.out.println("\n=== LISTANDO TODOS OS PRODUTOS ===");
            produtoDAO.buscarTodos().forEach(System.out::println);

            // Buscar por código
            System.out.println("\n=== BUSCAR POR CÓDIGO ===");
            Produto produtoBuscado = produtoDAO.buscarPorCodigo("PROD001");
            System.out.println("Produto encontrado: " + produtoBuscado);

            // Atualizar
            System.out.println("\n=== ATUALIZANDO PRODUTO ===");
            produtoBuscado.setPreco(new BigDecimal("3200.00"));
            produtoBuscado.setQuantidadeEstoque(8);
            produtoDAO.atualizar(produtoBuscado);
            System.out.println("Produto atualizado: " + produtoBuscado);

            // Buscar por nome
            System.out.println("\n=== BUSCAR POR NOME ===");
            produtoDAO.buscarPorNome("Mouse").forEach(System.out::println);

            // Buscar ativos
            System.out.println("\n=== PRODUTOS ATIVOS ===");
            produtoDAO.buscarAtivos().forEach(System.out::println);

            // Excluir produto
            System.out.println("\n=== EXCLUINDO PRODUTO ===");
            produtoDAO.excluir(p3.getId());
            System.out.println("Produto excluído com ID: " + p3.getId());

            // Listar novamente
            System.out.println("\n=== LISTANDO APÓS EXCLUSÃO ===");
            produtoDAO.buscarTodos().forEach(System.out::println);

        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();
        } finally {
            JPAUtil.closeEntityManagerFactory();
            System.out.println("\n=== APLICAÇÃO FINALIZADA ===");
        }
    }
}
