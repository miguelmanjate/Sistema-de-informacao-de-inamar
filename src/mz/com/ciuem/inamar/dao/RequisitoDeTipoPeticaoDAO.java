package mz.com.ciuem.inamar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import mz.com.ciuem.model.Peticao;
import mz.com.ciuem.model.Requisito;
import mz.com.ciuem.model.RequisitoDeTipoPeticao;

public class RequisitoDeTipoPeticaoDAO {
	
public static void adicionar(RequisitoDeTipoPeticao rtp){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("inamarDAO") ;
		EntityManager manager = factory. createEntityManager() ;
		
		manager. getTransaction() . begin() ;
		manager. persist(rtp) ;
		manager. getTransaction() . commit() ;
		
		manager.close();
	}
public static List<RequisitoDeTipoPeticao> listar(){
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("inamarDAO") ;
	EntityManager manager = factory. createEntityManager() ;
	
	List<RequisitoDeTipoPeticao> rtp = manager.createQuery("select r from RequisitoDeTipoPeticao as r").getResultList();
	manager.close();
	return rtp;	
}

public static RequisitoDeTipoPeticao getById(long id) {
	EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
	EntityManager manager = fatory.createEntityManager();
	
	RequisitoDeTipoPeticao r = manager.find(RequisitoDeTipoPeticao.class, id);
	return r;
}
public static void remover(long id){
	EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
	EntityManager manager = fatory.createEntityManager();
	
	RequisitoDeTipoPeticao encotrada = manager.find(RequisitoDeTipoPeticao.class, id);
	manager.getTransaction().begin();
	manager.remove(encotrada);
	manager.getTransaction().commit();
	
}
public static void atualizar(RequisitoDeTipoPeticao requisito){
	EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
	EntityManager manager = fatory.createEntityManager();
	
	manager.getTransaction().begin();
	manager.merge(requisito);
	manager.getTransaction().commit();
	manager.close();
}
public static List<RequisitoDeTipoPeticao> getBayDescricao(String descricao){
	 EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
	 EntityManager manager = fatory.createEntityManager();
	 
	 Query query = manager.createQuery("select rtp from RequisitoDeTipoPeticao rtp "+"where rtp.descricao like :paramDescricao");
	 query.setParameter("paramDescricao","%" + descricao + "%");
	 
	 List<RequisitoDeTipoPeticao> tpcs = query.getResultList();
	 manager.close();
	 return tpcs;
}
public static List<RequisitoDeTipoPeticao> getBayPeticao(Peticao peticao){
	EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
	 EntityManager manager = fatory.createEntityManager();
	 
	 Query query = manager.createQuery("select rtp from RequisitoDeTipoPeticao rtp where rtp.peticao =:peticao");
	 query.setParameter("peticao", peticao );
	 
	 List<RequisitoDeTipoPeticao> tpcs = query.getResultList();
	 manager.close();
	 return tpcs;
}
}
