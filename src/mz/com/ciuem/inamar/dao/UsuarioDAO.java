package mz.com.ciuem.inamar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import mz.com.ciuem.model.Usuario;

public class UsuarioDAO {
	
	public static void adicionar(Usuario usuario){
		
		EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
		EntityManager manager = fatory.createEntityManager();
		
		manager.getTransaction().begin();
		manager.persist(usuario);
		manager.getTransaction().commit();
		manager.close();
	}
	
	public static List<Usuario> listar(){
		EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
		EntityManager manager = fatory.createEntityManager();
		
		Query query = manager.createQuery("select u from Usuario u");
		
		return query.getResultList();
	}

	public static List<Usuario> getByLogin(String login){
		EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
		EntityManager manager = fatory.createEntityManager();
		
		Query query = manager.createQuery("select u from Usuario u where u.login like :login");
		query.setParameter("login","%"+ login +"%");
		return query.getResultList();
	}
	
	public static Usuario getByLoginSennha(String login, String senha) throws NoResultException{
		EntityManagerFactory fatory = Persistence.createEntityManagerFactory("inamarDAO");
		EntityManager manager = fatory.createEntityManager();
		
		Query query = manager.createQuery("select u from Usuario "
				+ "u where u.login =:login and u.senha =:senha ");

		query.setParameter("login", login);
		query.setParameter("senha", senha);
		
		return (Usuario) query.getSingleResult();
	}
}
