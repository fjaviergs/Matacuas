package es.upm.dit.isst.matacuas.dao;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.matacuas.model.Usuario;

public interface UsuarioDAO {

	public List<Usuario> listUsuarios();
	
	public void add (String nusuario, String nombre, String apellidos,
			String contraseña, String matricula);
	
	public void update (Long id, String nusuario, String nombre, String apellidos,
			String contraseña, String matricula);
	
	public Usuario getUsuario(Long usuarioId);
	
	public Usuario getUsuario(String nusuario, String contraseña);	

	public void remove (Long id);
			
}