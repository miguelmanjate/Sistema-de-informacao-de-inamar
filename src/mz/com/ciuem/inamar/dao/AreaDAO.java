package mz.com.ciuem.inamar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import mz.com.ciuem.model.Area;
import mz.com.ciuem.model.Orgao;


public class AreaDAO {
	
	public static void adicionar(Area area){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("inamarDAO") ;
		EntityManager manager = factory. createEntityManager() ;
		
		manager. getTransaction() . begin() ;
		manager. persist(area) ;
		manager. getTransaction() . commit() ;
		
		manager.close();
	}
	public static List<Area> listar(){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("inamarDAO") ;
		EntityManager manager = factory. createEntityManager() ;
		
		List<Area> lista = manager.createQuery("select a from Area as a").getResultList();
		
		return lista;
	}

	public static Area getById(long id){
		EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
		EntityManager manager = fatory.createEntityManager();
		
		Area encotrada = manager.find(Area.class, id);
		return encotrada;
	}
	public static void remover(long id){
		EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
		EntityManager manager = fatory.createEntityManager();
		
		Area encotrada = manager.find(Area.class, id);
		manager.getTransaction().begin();
		manager.remove(encotrada);
		manager.getTransaction().commit();
		
	}
	public static void atualizar(Area area){
		EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
		EntityManager manager = fatory.createEntityManager();
		
		manager.getTransaction().begin();
		manager.merge(area);
		manager.getTransaction().commit();
		manager.close();
	}
	public static List<Area> getBayCodigo(String codigo){
		EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
		EntityManager manager = fatory.createEntityManager();
		
		Query query = manager.createQuery("select a from Area as a "+"where a.codigo like :paramCodigo");
		query.setParameter("paramCodigo", "%" + codigo + "%");
		
		List<Area> areas = query.getResultList();
		
		return areas;
	}
	public static List<Area> getBayDescricao(String descricao){
		EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
		EntityManager manager = fatory.createEntityManager();
		
		Query query = manager.createQuery("select a from Area as a "+"where a.descricao like :paramDescricao");
		query.setParameter("paramDescricao", "%" + descricao + "%");
		
		List<Area> areas = query.getResultList();
		return areas;
	}
	public static List<Area> getBayOrgaoId(Orgao orgao){
		
		EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
		EntityManager manager = fatory.createEntityManager();
		
		Query query = manager.createQuery("Select a from Area a where a.orgao=:orgao");
		query.setParameter("orgao", orgao);
		List<Area> areas = query.getResultList();
		return areas;
	}
}
