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
	public void add(String nusuario, String nombre, String apellidos,
			String contraseña, String matricula) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Usuario usuario = new Usuario(nusuario, nombre, apellidos,
					contraseña, matricula);
			em.persist(usuario);
			em.close();
		}
	}
	
	@Override
	public void update(Long id, String nusuario, String nombre, String apellidos,
			String contraseña, String matricula) {
		EntityManager em = EMFService.get().createEntityManager();
		Usuario usuario = em.find(Usuario.class, id);
		usuario.setNUsuario(nusuario);
		usuario.setNombre(nombre);
		usuario.setApellidos(apellidos);
		usuario.setContraseña(contraseña);
		usuario.setMatricula(matricula);
		em.merge(usuario);
		em.close();
	}

	@Override
	public Usuario getUsuario(Long id) {
		EntityManager em = EMFService.get().createEntityManager();
		Usuario usuario = em.find(Usuario.class, id);
		return usuario;
	}
	

	@Override
	public void remove(Long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Usuario usuario = em.find(Usuario.class, id);
			em.remove(usuario);
		} finally {
			em.close();
		}
	}

	@Override
	public Usuario getUsuario(String nusuario, String contraseña) {
			EntityManager em = EMFService.get().createEntityManager();
			Query q = em.createQuery("select u from Usuario u where u.nusuario = :nusuario and u.contraseña = :contraseña");
			q.setParameter("nusuario", nusuario);
			q.setParameter("contraseña", contraseña);
			List<Usuario> lista = q.getResultList();
			if(lista.isEmpty()){
				return null;
			}
			Usuario usuario = lista.get(0);
			return usuario;
	}
	
}

