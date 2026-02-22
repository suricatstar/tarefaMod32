package main.java.br.com.ebac.dao;

import java.util.List;

public interface IGenericDAO<T> {
    T cadastrar(T entity);

    T buscarPorId(Long id);

    List<T> buscarTodos();

    T atualizar(T entity);

    void excluir(Long id);
}
