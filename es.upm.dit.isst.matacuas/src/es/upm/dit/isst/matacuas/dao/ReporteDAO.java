package es.upm.dit.isst.matacuas.dao;

import java.util.HashMap;
import java.util.List;

import es.upm.dit.isst.matacuas.model.Reporte;

public interface ReporteDAO {

	public List<Reporte> listReportes();
	
	public void add (String matricula, String descripcion, String lugar, Boolean esPositivo);
	
	public void update (Long id, String matricula, String descripcion, String lugar, Boolean esPositivo);
	
	public Reporte getReporte(Long reporteId);

	public void remove (Long id);
			
}
