package es.upm.dit.isst.matacuas;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import es.upm.dit.isst.matacuas.model.Reporte;
import es.upm.dit.isst.matacuas.dao.ReporteDAO;
import es.upm.dit.isst.matacuas.dao.ReporteDAOImpl;

public class DetalleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		/*
		 * recuperar datos del reporte
		 */		
		Long reporteId = Long.parseLong(req.getParameter("id"));
		
		ReporteDAO dao = ReporteDAOImpl.getInstance();
		Reporte reporte = dao.getReporte(reporteId);		

		req.getSession().setAttribute("reporte", reporte);
		
		RequestDispatcher view = req.getRequestDispatcher("detalle.jsp");
        view.forward(req, resp);
	}
	
} 