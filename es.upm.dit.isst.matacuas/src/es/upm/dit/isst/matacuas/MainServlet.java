package es.upm.dit.isst.matacuas;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.upm.dit.isst.matacuas.model.Reporte;
import es.upm.dit.isst.matacuas.dao.ReporteDAO;
import es.upm.dit.isst.matacuas.dao.ReporteDAOImpl;

public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		/*
		 * reinicio atributos de sesión
		 */
		req.getSession().setAttribute("mensajeInfo", null);
		req.getSession().setAttribute("reportes", null);
		req.getSession().setAttribute("mensajeError", null);
		req.getSession().setAttribute("count", null);
		
		ReporteDAO reporteDao = ReporteDAOImpl.getInstance();
		
		List<Reporte> reportes = reporteDao.listReportes();
		req.getSession().setAttribute("reportes", new ArrayList<Reporte>(reportes));
		
		RequestDispatcher view = req.getRequestDispatcher("main.jsp");
        view.forward(req, resp);
	}
}
