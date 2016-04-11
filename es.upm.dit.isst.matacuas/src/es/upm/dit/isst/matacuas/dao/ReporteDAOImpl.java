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
		Query q = em.createQuery("select m from Reporte m");
		List<Reporte> reportes = q.getResultList();
		return reportes;
	}

	@Override
	public void add(String googleID, String matricula, String descripcion, String lugar, byte[] imagen, Boolean esPositivo) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Reporte reporte = new Reporte(googleID, matricula, descripcion, lugar, imagen, esPositivo);
			em.persist(reporte);
			em.close();
		}
	}
	
	@Override
	public void update(Long id, String googleID, String matricula, String descripcion, String lugar, byte[] imagen, Boolean esPositivo) {
		EntityManager em = EMFService.get().createEntityManager();
		Reporte reporte = em.find(Reporte.class, id);
		reporte.setGoogleID(googleID);
		reporte.setMatricula(matricula);
		reporte.setDescripcion(descripcion);
		reporte.setLugar(lugar);
		reporte.setImagen(imagen);
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

	@Override
	public List<Reporte> getReportesConMatricula(String matricula) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m from Reporte m where m.matricula = :matricula");
		q.setParameter("matricula", matricula);
		List<Reporte> reportes = q.getResultList();
		return reportes;
	}

	@Override
	public List<Reporte> getReportesConGoogleID(String googleID) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m from Reporte m where m.googleID = :googleID");
		q.setParameter("googleID", googleID);
		List<Reporte> reportes = q.getResultList();
		return reportes;
	}

	
}

