package mz.com.ciuem.inamar.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import mz.com.ciuem.model.Orgao;
import mz.com.ciuem.model.Peticao;

public class PeticaoDAO {

	public static void adicionar(Peticao peticao) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("inamarDAO");
		EntityManager manager = factory.createEntityManager();

		manager.getTransaction().begin();
		manager.persist(peticao);
		manager.getTransaction().commit();

		manager.close();
	}

	public static List<Peticao> listar() {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("inamarDAO");
		EntityManager manager = factory.createEntityManager();

		List<Peticao> lista = manager.createQuery("select p from Peticao as p").getResultList();
		manager.close();
		return lista;

	}

	public static Peticao getById(long id) {
		EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
		EntityManager manager = fatory.createEntityManager();
		
		Peticao p = manager.find(Peticao.class, id);
		return p;
	}
	public static void remover(long id){
		EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
		EntityManager manager = fatory.createEntityManager();
		
		Peticao encotrada = manager.find(Peticao.class, id);
		manager.getTransaction().begin();
		manager.remove(encotrada);
		manager.getTransaction().commit();
		
	}
	public static void atualizar(Peticao peticao){
		EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
		EntityManager manager = fatory.createEntityManager();
		
		manager.getTransaction().begin();
		manager.merge(peticao);
		manager.getTransaction().commit();
		manager.close();
	}
	public static Peticao getByCodigo(String codigo) throws NoResultException {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("inamarDAO");
		EntityManager manager = factory.createEntityManager();

		Query query = manager.createQuery("select p from Peticao as p " + "where	p.codigo = :paramCodigo");
		query.setParameter("paramCodigo", codigo);
		Peticao p = (Peticao) query.getSingleResult();
		manager.close();
		return p;
	}

	public static List<Peticao> getBayCodigo(String codigo) {
		EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
		EntityManager manager = fatory.createEntityManager();

		Query query = manager.createQuery("select p from Peticao p " + "where p.codigo like :paramCodigo");
		query.setParameter("paramCodigo", "%" + codigo + "%");

		List<Peticao> lista = query.getResultList();
		manager.close();
		return lista;
	}

	public static List<Peticao> getBayDescricao(String descricao) {
		EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
		EntityManager manager = fatory.createEntityManager();

		Query query = manager.createQuery("select p from Peticao p " + "where p.descricao like :paramDescricao");
		query.setParameter("paramDescricao", "%" + descricao + "%");
		List<Peticao> peticoes = query.getResultList();
		manager.close();
		return peticoes;
	}
}
