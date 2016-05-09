package es.upm.dit.isst.matacuas.dao;

import java.util.List;

import com.google.appengine.api.datastore.Text;

import es.upm.dit.isst.matacuas.model.Reporte;

public interface ReporteDAO {

	public List<Reporte> listReportes();
	
	public void add (String googleID, String matricula, String descripcion, String lugar, Text imagenB64, Boolean esPositivo);
	
	public void update (Long id, String googleID, String matricula, String descripcion, String lugar, Text imagenB64, Boolean esPositivo);
	
	public Reporte getReporte(Long reporteId);
	
	public List<Reporte> getReportesConMatricula(String matricula);
	
	public List<Reporte> getReportesConGoogleID(String googleID);

	public void remove (Long id);
			
}
