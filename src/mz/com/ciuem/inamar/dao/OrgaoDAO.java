package mz.com.ciuem.inamar.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import mz.com.ciuem.model.Orgao;

public class OrgaoDAO {
	
	public static void adicionar(Orgao orgao){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("inamarDAO") ;
		EntityManager manager = factory. createEntityManager() ;
		
		manager. getTransaction() . begin() ;
		manager. persist(orgao) ;
		manager. getTransaction() . commit() ;
		
		manager.close();
	}
	
	public static List<Orgao> listar(){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("inamarDAO") ;
		EntityManager manager = factory. createEntityManager() ;
		
		List<Orgao> lista = manager.createQuery("select o from Orgao as o").getResultList();
		
		return lista;
		
	}
	public static Orgao getBayId(long l){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("inamarDAO") ;
		EntityManager manager = factory. createEntityManager() ;
		
		Orgao orgao = manager.find(Orgao.class, l);
		return orgao;
		
	}

	public static List<Orgao> getBayDescricao(String descricao){
		
		EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
		EntityManager manager = fatory.createEntityManager();
		
		Query query = manager.createQuery("select o from Orgao as o "+
		"where o.descricao like :paramDescricao");
		
		query.setParameter("paramDescricao", "%" + descricao + "%");
		List<Orgao> orgaes = query.getResultList();
		return orgaes;
	}
	public static List<Orgao> getBayLocal(String local){
		EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
		EntityManager manager = fatory.createEntityManager();
		
		Query query = manager.createQuery("select o from Orgao as o "+"where o.local like :paramLocal");
		query.setParameter("paramLocal","%" + local + "%");
		List<Orgao> orgaoes = query.getResultList();
		return orgaoes;
	}
	public static List<Orgao> getBayConta(long conta){
		
		EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
		EntityManager manager = fatory.createEntityManager();
		
		Query query = manager.createQuery("select o from Orgao as o "+"where o.conta = :paramConta");
		query.setParameter("paramConta", conta );
		List<Orgao> orgaoes = query.getResultList();
		return orgaoes;
	}
}
