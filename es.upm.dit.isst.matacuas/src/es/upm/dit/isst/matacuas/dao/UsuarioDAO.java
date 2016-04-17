package es.upm.dit.isst.matacuas.dao;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.matacuas.model.Usuario;

public interface UsuarioDAO {

	public List<Usuario> listUsuarios();
	
	public void add (String googleID, String email, String matricula);
	
	public void update (String googleID, String email, String matricula);
	
	public Usuario getUsuario(String googleID);

	public void remove (String googleID);
			
}