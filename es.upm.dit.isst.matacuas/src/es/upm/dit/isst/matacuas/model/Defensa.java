package es.upm.dit.isst.matacuas.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Defensa implements Serializable {


	private static final Long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long reporteID;
	private String defensor;
	private String descripcion;
	public Defensa(Long reporteID, String defensor, String descripcion) {
		super();
		this.reporteID = reporteID;
		this.defensor = defensor;
		this.descripcion = descripcion;
	}
	public Long getReporteID() {
		return reporteID;
	}
	public void setReporteID(Long reporteID) {
		this.reporteID = reporteID;
	}
	public String getDefensor() {
		return defensor;
	}
	public void setDefensor(String defensor) {
		this.defensor = defensor;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}