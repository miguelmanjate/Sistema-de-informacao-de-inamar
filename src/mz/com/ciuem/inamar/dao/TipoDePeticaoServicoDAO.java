package mz.com.ciuem.inamar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import mz.com.ciuem.model.Peticao;
import mz.com.ciuem.model.Servico;
import mz.com.ciuem.model.TipoDePeticaoServico;

public class TipoDePeticaoServicoDAO {
	
public static void adicionar(TipoDePeticaoServico tps){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("inamarDAO") ;
		EntityManager manager = factory. createEntityManager() ;
		
		manager. getTransaction() . begin() ;
		manager. persist(tps) ;
		manager. getTransaction() . commit() ;
		
		manager.close();
	}

public static TipoDePeticaoServico getById(long id) {
	EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
	EntityManager manager = fatory.createEntityManager();
	
	TipoDePeticaoServico tps = manager.find(TipoDePeticaoServico.class, id);
	return tps;
}
public static void remover(long id){
	EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
	EntityManager manager = fatory.createEntityManager();
	
	TipoDePeticaoServico encotrada = manager.find(TipoDePeticaoServico.class, id);
	manager.getTransaction().begin();
	manager.remove(encotrada);
	manager.getTransaction().commit();
	
}
public static void atualizar(TipoDePeticaoServico tps){
	EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
	EntityManager manager = fatory.createEntityManager();
	
	manager.getTransaction().begin();
	manager.merge(tps);
	manager.getTransaction().commit();
	manager.close();
}

	public static List<TipoDePeticaoServico> listar(){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("inamarDAO") ;
		EntityManager manager = factory. createEntityManager() ;
		
		List<TipoDePeticaoServico> lista = manager.createQuery("select t from TipoDePeticaoServico as t").getResultList();
		manager.close();
		return lista;	
	}
 public static List<TipoDePeticaoServico> getBayDescricao(String descricao){
	 EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
	 EntityManager manager = fatory.createEntityManager();
	 
	 Query query = manager.createQuery("select tpc from TipoDePeticaoServico tpc "+"where tpc.descricao like :paramDescricao");
	 query.setParameter("paramDescricao","%" + descricao + "%");
	 
	 List<TipoDePeticaoServico> tpcs = query.getResultList();
	 manager.close();
	 return tpcs;
 }
 public static List<TipoDePeticaoServico> getBayValor(double valor){
	 EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
	 EntityManager manager = fatory.createEntityManager();
	 
	 Query query = manager.createQuery("select tpc from TipoDePeticaoServico tpc "+"where tpc.valor =:paramValor");
	 query.setParameter("paramValor", valor);
	 List<TipoDePeticaoServico> tpcs = query.getResultList();
	 manager.close();
	 return tpcs;
 }
 
 public static List<TipoDePeticaoServico> getBayPeticao(Peticao peticao){
	 EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
	 EntityManager manager = fatory.createEntityManager();
	 
	 Query query = manager.createQuery("select tpc from TipoDePeticaoServico tpc where tpc.peticao =:peticao");
	 query.setParameter("peticao", peticao);
	 
	 List<TipoDePeticaoServico> tpcs = query.getResultList();
	 manager.close();
	 return tpcs;
 }
}
