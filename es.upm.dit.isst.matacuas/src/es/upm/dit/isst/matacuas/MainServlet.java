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
		 * reinicio atributos de sesi�n
		 */
		req.getSession().setAttribute("mensajeInfo", null);
		req.getSession().setAttribute("reportes", null);
		req.getSession().setAttribute("mensajeError", null);
		req.getSession().setAttribute("count", null);
		req.getSession().setAttribute("admin", null);

UserService userService = UserServiceFactory.getUserService();
		
		/*
		 * compruebo sesi�n
		 */
        if (req.getUserPrincipal() == null) {
        	// si no est� logueado le doy la opci�n de hacerlo
        	String thisURL = req.getRequestURI();
            String urlLogIn = userService.createLoginURL(thisURL);
            req.getSession().setAttribute("urlLogIn", urlLogIn); 
            RequestDispatcher view = req.getRequestDispatcher("login.jsp");
            view.forward(req, resp);
        }else if(userService.isUserAdmin()){
        		//es administrador
        	req.getSession().setAttribute("admin", true);
        }
		
		ReporteDAO reporteDao = ReporteDAOImpl.getInstance();
		
		List<Reporte> reportes = reporteDao.listReportes();
		req.getSession().setAttribute("reportes", new ArrayList<Reporte>(reportes));
		
		RequestDispatcher view = req.getRequestDispatcher("main.jsp");
        view.forward(req, resp);
	}
}
