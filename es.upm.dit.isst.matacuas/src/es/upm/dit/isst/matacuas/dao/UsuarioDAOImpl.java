package es.upm.dit.isst.matacuas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.matacuas.model.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

	
	private static UsuarioDAOImpl instance;

	private UsuarioDAOImpl() {
	}
	
	public static UsuarioDAOImpl getInstance(){
		if (instance == null)
			instance = new UsuarioDAOImpl();
		return instance;
	}
	@Override
	public List<Usuario> listUsuarios() {
		EntityManager em = EMFService.get().createEntityManager();
		// read the existing entries
		Query q = em.createQuery("select m from Usuario m");
		List<Usuario> usuario = q.getResultList();
		return usuario;
	}

	@Override
	public void add(String googleID, String email, String matricula) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Usuario usuario = new Usuario(googleID,email,matricula);
			em.persist(usuario);
			em.close();
		}
	}
	
	@Override
	public void update(String googleID, String email, String matricula) {
		EntityManager em = EMFService.get().createEntityManager();
		Usuario usuario = em.find(Usuario.class, googleID);
		usuario.setEmail(email);
		usuario.setMatricula(matricula);
		em.merge(usuario);
		em.close();
	}

	@Override
	public Usuario getUsuario(String googleID) {
		EntityManager em = EMFService.get().createEntityManager();
		Usuario usuario = em.find(Usuario.class, googleID);
		return usuario;
	}
	

	@Override
	public void remove(String googleID) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Usuario usuario = em.find(Usuario.class, googleID);
			em.remove(usuario);
		} finally {
			em.close();
		}
	}

	@Override
	public String getEmailConMatricula(String matricula) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m.email from Usuario m where m.matricula = :matricula");
		q.setParameter("matricula", matricula);
		List<String> resultado = q.getResultList();
		if(resultado.isEmpty()){return null;}else{return resultado.get(0);}
	}

}

