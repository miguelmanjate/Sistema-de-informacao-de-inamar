package mz.com.ciuem.inamar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import mz.com.ciuem.model.RequisitoDeTipoPeticao;
import mz.com.ciuem.model.Servico;

public class ServicoDAO {
	
public static void adicionar(Servico servico){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("inamarDAO") ;
		EntityManager manager = factory. createEntityManager() ;
		
		manager. getTransaction() . begin() ;
		manager. persist(servico) ;
		manager. getTransaction() . commit() ;
		
		manager.close();
	}

public static Servico getById(long id) {
	EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
	EntityManager manager = fatory.createEntityManager();
	
	Servico s = manager.find(Servico.class, id);
	return s;
}
public static void remover(long id){
	EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
	EntityManager manager = fatory.createEntityManager();
	
	Servico encotrada = manager.find(Servico.class, id);
	manager.getTransaction().begin();
	manager.remove(encotrada);
	manager.getTransaction().commit();
	
}
public static void atualizar(Servico servico){
	EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
	EntityManager manager = fatory.createEntityManager();
	
	manager.getTransaction().begin();
	manager.merge(servico);
	manager.getTransaction().commit();
	manager.close();
}

	public static List<Servico> listar(){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("inamarDAO") ;
		EntityManager manager = factory. createEntityManager() ;
		
		List<Servico> lista = manager.createQuery("select s from Servico as s").getResultList();
		
		return lista;
		
	}
 public static List<Servico> getByDescricao(String descricao){
	 EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
	 EntityManager manager = fatory.createEntityManager();
	 
	 Query query = manager.createQuery("select s from Servico s "+"where s.descricao like :paramDescricao");
	 query.setParameter("paramDescricao","%" + descricao + "%");
	 
	 List<Servico> servicos = query.getResultList();
	 return servicos;
 }

}
