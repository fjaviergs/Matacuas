package es.upm.dit.isst.matacuas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.matacuas.model.Reporte;

public class ReporteDAOImpl implements ReporteDAO {

	
	private static ReporteDAOImpl instance;

	private ReporteDAOImpl() {
	}
	
	public static ReporteDAOImpl getInstance(){
		if (instance == null)
			instance = new ReporteDAOImpl();
		return instance;
	}
	@Override
	public List<Reporte> listReportes() {
		EntityManager em = EMFService.get().createEntityManager();
		// read the existing entries
		Query q = em.createQuery("select m from Reporte m");
		List<Reporte> reportes = q.getResultList();
		return reportes;
	}

	@Override
	public void add(String matricula, String descripcion, String lugar, Boolean esPositivo) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Reporte reporte = new Reporte(matricula, descripcion, lugar, esPositivo);
			em.persist(reporte);
			em.close();
		}
	}
	
	@Override
	public void update(Long id, String matricula, String descripcion, String lugar, Boolean esPositivo) {
		EntityManager em = EMFService.get().createEntityManager();
		Reporte reporte = em.find(Reporte.class, id);
		reporte.setMatricula(matricula);
		reporte.setDescripcion(descripcion);
		reporte.setLugar(lugar);
		reporte.setEsPositivo(esPositivo);
		em.merge(reporte);
		em.close();
	}

	@Override
	public Reporte getReporte(Long id) {
		EntityManager em = EMFService.get().createEntityManager();
		Reporte reporte = em.find(Reporte.class, id);
		return reporte;
	}
	

	@Override
	public void remove(Long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Reporte reporte = em.find(Reporte.class, id);
			em.remove(reporte);
		} finally {
			em.close();
		}
	}

	
}

