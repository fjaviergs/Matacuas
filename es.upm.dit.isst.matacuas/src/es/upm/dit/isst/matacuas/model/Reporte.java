package es.upm.dit.isst.matacuas.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Text;

@Entity
public class Reporte implements Serializable, Comparable<Reporte>{


	private static final Long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String googleID;
	private String matricula;
	private String descripcion; 
	private String lugar;
	private Text imagenB64;
	private boolean esPositivo;
	public Date fecha;
	

	public Reporte(String googleID, String matricula, String descripcion,
			String lugar, Text imagenB64, boolean esPositivo, Date fecha) {
		super();
		this.googleID = googleID;
		this.matricula = matricula;
		this.descripcion = descripcion;
		this.lugar = lugar;
		this.imagenB64 = imagenB64;
		this.esPositivo = esPositivo;
		this.fecha = fecha;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getLugar() {
		return lugar;
	}
	
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	
	public boolean isEsPositivo() {
		return esPositivo;
	}
	
	public void setEsPositivo(boolean esPositivo) {
		this.esPositivo = esPositivo;
	}
	
	public static Long getSerialversionuid() {
		return serialVersionUID;
	}

	public Text getImagen() {
		return imagenB64;
	}

	public void setImagen(Text imagenB64) {
		this.imagenB64 = imagenB64;
	}

	public String getGoogleID() {
		return googleID;
	}

	public void setGoogleID(String googleID) {
		this.googleID = googleID;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	// Los reportes se comparan solamente por su fecha para ordenarlos.
	@Override
	public int compareTo(Reporte r) {
		try {
			int n = fecha.compareTo(r.getFecha());
			if (n == 0) {
				return 0;
			} else if (n > 0) {
				return -1;
			} else if (n < 0) {
				return 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Error al comparar.");
		return 0;
	}
}
