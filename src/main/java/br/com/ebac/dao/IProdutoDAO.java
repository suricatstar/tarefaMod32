package main.java.br.com.ebac.dao;

import main.java.br.com.ebac.model.Produto;
import java.util.List;

public interface IProdutoDAO extends IGenericDAO<Produto> {
    Produto buscarPorCodigo(String codigo);

    List<Produto> buscarPorNome(String nome);

    List<Produto> buscarAtivos();
}
