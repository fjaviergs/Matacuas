package es.upm.dit.isst.matacuas;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.matacuas.model.Reporte;
import es.upm.dit.isst.matacuas.dao.ReporteDAO;
import es.upm.dit.isst.matacuas.dao.ReporteDAOImpl;

public class DetalleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
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
        }
		
		/*
		 * recuperar datos del reporte
		 */		
		Long reporteId = Long.parseLong(req.getParameter("id"));
		String mio = req.getParameter("mio");
		
		ReporteDAO dao = ReporteDAOImpl.getInstance();
		Reporte reporte = dao.getReporte(reporteId);
		
		/*
		 * Por ahora todos los reportes que no sean mios son defendibles
		 * se puede cambiar en un futuro para no permitir enviar más de una defensa
		 */
		req.getSession().setAttribute("defendible", true);
		
		if(mio !=null && mio.equals("true")){
			//si el reporte lo he realizado yo no puedo defenderlo
			req.getSession().setAttribute("defendible", false);
		}

		req.getSession().setAttribute("reporte", reporte);
		
		RequestDispatcher view = req.getRequestDispatcher("detalle.jsp");
        view.forward(req, resp);
	}
	
} 