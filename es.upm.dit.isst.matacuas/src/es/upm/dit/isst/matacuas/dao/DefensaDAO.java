package es.upm.dit.isst.matacuas.dao;

import java.util.List;

import es.upm.dit.isst.matacuas.model.Defensa;

public interface DefensaDAO {

	public List<Defensa> listDefensas();
	
	public void add (Long reporteID, String defensor, String descripcion);
	
	public void update (Long id, Long reporteID, String defensor, String descripcion);
	
	public Defensa getDefensa(Long defensaId);
	
	public List<Defensa> getDefensasDelReporte(Long reporteID);

	public void remove (Long id);
			
}