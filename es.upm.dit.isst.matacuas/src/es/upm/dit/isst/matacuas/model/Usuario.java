package es.upm.dit.isst.matacuas.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario implements Serializable {

	private static final Long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nusuario;
	private String nombre;
	private String apellidos;
	private String contraseña;
	private String matricula;
	
	public Usuario(String usuario, String nombre, String apellidos,
			String contraseña, String matricula) {
		this.nusuario = usuario;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.contraseña = contraseña;
		this.matricula = matricula;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNUsuario() {
		return nusuario;
	}

	public void setNUsuario(String nusuario) {
		this.nusuario = nusuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public static Long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
}
