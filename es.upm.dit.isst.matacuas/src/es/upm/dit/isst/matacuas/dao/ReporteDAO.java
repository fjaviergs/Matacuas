package es.upm.dit.isst.matacuas.dao;

import java.util.HashMap;
import java.util.List;

import es.upm.dit.isst.matacuas.model.Reporte;

public interface ReporteDAO {

	public List<Reporte> listReportes();
	
	public void add (String matricula, String descripcion, String lugar, byte[] imagen, Boolean esPositivo);
	
	public void update (Long id, String matricula, String descripcion, String lugar, byte[] imagen, Boolean esPositivo);
	
	public Reporte getReporte(Long reporteId);
	
	public List<Reporte> getReportesConMatricula(String matricula);

	public void remove (Long id);
			
}
