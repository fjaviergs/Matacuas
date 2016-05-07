package es.upm.dit.isst.matacuas;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.util.ArrayList;
import java.util.List;

import es.upm.dit.isst.matacuas.model.Reporte;
import es.upm.dit.isst.matacuas.dao.ReporteDAO;
import es.upm.dit.isst.matacuas.dao.ReporteDAOImpl;

public class AdminReportesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		UserService userService = UserServiceFactory.getUserService();
		
		/*
		 * compruebo sesión
		 */
        if (req.getUserPrincipal() == null) {
        	// si no está logueado le doy la opción de hacerlo
        	String thisURL = req.getRequestURI();
            String urlLogIn = userService.createLoginURL(thisURL);
            req.getSession().setAttribute("urlLogIn", urlLogIn); 
            RequestDispatcher view = req.getRequestDispatcher("login.jsp");
            view.forward(req, resp);
        }else if(!userService.isUserAdmin()){
        		//no es administrador
        	RequestDispatcher view = req.getRequestDispatcher("redirect.jsp");
            view.forward(req, resp);
        }
        
		ReporteDAO reporteDao = ReporteDAOImpl.getInstance();
		List<Reporte> lista = reporteDao.listReportes();
		req.getSession().setAttribute("reportes", new ArrayList<Reporte>(lista));
		RequestDispatcher view = req.getRequestDispatcher("adminreportes.jsp");
        view.forward(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		UserService userService = UserServiceFactory.getUserService();
		
		/*
		 * compruebo sesión
		 */
        if (req.getUserPrincipal() == null) {
        	// si no está logueado le doy la opción de hacerlo
        	String thisURL = req.getRequestURI();
            String urlLogIn = userService.createLoginURL(thisURL);
            req.getSession().setAttribute("urlLogIn", urlLogIn); 
            RequestDispatcher view = req.getRequestDispatcher("login.jsp");
            view.forward(req, resp);
        }else if(!userService.isUserAdmin()){
        		//no es administrador
        	RequestDispatcher view = req.getRequestDispatcher("redirect.jsp");
            view.forward(req, resp);
        }
		
		/*
		 * recuperar datos del reporte
		 */		
		Long reporteId = Long.parseLong(req.getParameter("id"));
	
		//elimino el reporte
		ReporteDAO dao = ReporteDAOImpl.getInstance();
		dao.remove(reporteId);
		
		req.getSession().setAttribute("mensajeInfo", "Reporte eliminado con exito");
		RequestDispatcher view = req.getRequestDispatcher("redirect.jsp");
        view.forward(req, resp);
	}

} 