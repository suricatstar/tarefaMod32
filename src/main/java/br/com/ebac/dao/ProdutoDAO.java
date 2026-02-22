package main.java.br.com.ebac.dao;

import main.java.br.com.ebac.model.Produto;
import main.java.br.com.ebac.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


public class ProdutoDAO implements IProdutoDAO {

    @Override
    public Produto cadastrar(Produto produto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(produto);
            em.getTransaction().commit();
            return produto;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao cadastrar produto", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Produto buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Produto.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Produto> buscarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Produto> query = em.createQuery("SELECT p FROM Produto p", Produto.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Produto atualizar(Produto produto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Produto produtoAtualizado = em.merge(produto);
            em.getTransaction().commit();
            return produtoAtualizado;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao atualizar produto", e);
        } finally {
            em.close();
        }
    }

    @Override
    public void excluir(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Produto produto = em.find(Produto.class, id);
            if (produto != null) {
                em.remove(produto);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao excluir produto", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Produto buscarPorCodigo(String codigo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Produto> query = em.createQuery(
                    "SELECT p FROM Produto p WHERE p.codigo = :codigo", Produto.class);
            query.setParameter("codigo", codigo);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Produto> buscarPorNome(String nome) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Produto> query = em.createQuery(
                    "SELECT p FROM Produto p WHERE p.nome LIKE :nome", Produto.class);
            query.setParameter("nome", "%" + nome + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Produto> buscarAtivos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Produto> query = em.createQuery(
                    "SELECT p FROM Produto p WHERE p.ativo = true", Produto.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
