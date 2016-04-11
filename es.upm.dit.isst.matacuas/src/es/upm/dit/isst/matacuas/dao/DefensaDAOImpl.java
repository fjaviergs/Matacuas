package es.upm.dit.isst.matacuas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.matacuas.model.Defensa;

public class DefensaDAOImpl implements DefensaDAO {

	
	private static DefensaDAOImpl instance;

	private DefensaDAOImpl() {
	}
	
	public static DefensaDAOImpl getInstance(){
		if (instance == null)
			instance = new DefensaDAOImpl();
		return instance;
	}
	@Override
	public List<Defensa> listDefensas() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m from Defensa m");
		List<Defensa> Defensas = q.getResultList();
		return Defensas;
	}

	@Override
	public void add(Long reporteID, String defensor, String descripcion) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Defensa defensa = new Defensa(reporteID, defensor, descripcion);
			em.persist(defensa);
			em.close();
		}
	}
	
	@Override
	public void update(Long id,Long reporteID, String defensor, String descripcion) {
		EntityManager em = EMFService.get().createEntityManager();
		Defensa defensa = em.find(Defensa.class, id);
		defensa.setReporteID(reporteID);
		defensa.setDefensor(defensor);
		defensa.setDescripcion(descripcion);
		em.merge(defensa);
		em.close();
	}

	@Override
	public Defensa getDefensa(Long id) {
		EntityManager em = EMFService.get().createEntityManager();
		Defensa defensa = em.find(Defensa.class, id);
		return defensa;
	}
	

	@Override
	public void remove(Long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Defensa defensa = em.find(Defensa.class, id);
			em.remove(defensa);
		} finally {
			em.close();
		}
	}

	@Override
	public List<Defensa> getDefensasDelReporte(Long reporteID) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m from Defensa m where m.reporteID = :reporteID");
		q.setParameter("reporteID", reporteID);
		List<Defensa> defensas = q.getResultList();
		return defensas;
	}
	
}