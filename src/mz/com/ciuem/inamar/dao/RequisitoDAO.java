package mz.com.ciuem.inamar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import mz.com.ciuem.model.Peticao;
import mz.com.ciuem.model.Requisito;

public class RequisitoDAO {
	
	public static void adicionar(Requisito requisito){
		
		EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
		EntityManager manager = fatory.createEntityManager();
		
		manager.getTransaction().begin();
		manager.persist(requisito);
		manager.getTransaction().commit();
		manager.close();
	}
    public static List<Requisito> listar(){
    	EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
    	EntityManager manager = fatory.createEntityManager();
    	
    	Query query = manager.createQuery("select r from Requisito r");
    	List<Requisito> requisitos = query.getResultList();
    	
    	manager.close();
    	return requisitos;
    }
	public static Requisito getById(long id) {
		EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
		EntityManager manager = fatory.createEntityManager();
		
		Requisito r = manager.find(Requisito.class, id);
		return r;
	}
	public static void remover(long id){
		EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
		EntityManager manager = fatory.createEntityManager();
		
		Requisito encotrada = manager.find(Requisito.class, id);
		manager.getTransaction().begin();
		manager.remove(encotrada);
		manager.getTransaction().commit();
		
	}
	public static void atualizar(Requisito requisito){
		EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
		EntityManager manager = fatory.createEntityManager();
		
		manager.getTransaction().begin();
		manager.merge(requisito);
		manager.getTransaction().commit();
		manager.close();
	}
    public static List<Requisito> getByDescricao(String descricao){
    	EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
    	EntityManager manager = fatory.createEntityManager();
    	
    	Query query = manager.createQuery("select r from Requisito r "+"where r.descricao like :paramDescricao");
    	query.setParameter("paramDescricao","%" + descricao + "%");
    	
    	List<Requisito> requistos = query.getResultList();
    	return requistos;
    }
}
