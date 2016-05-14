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
import es.upm.dit.isst.matacuas.model.Usuario;
import es.upm.dit.isst.matacuas.dao.ReporteDAO;
import es.upm.dit.isst.matacuas.dao.ReporteDAOImpl;
import es.upm.dit.isst.matacuas.dao.UsuarioDAO;
import es.upm.dit.isst.matacuas.dao.UsuarioDAOImpl;

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
		req.getSession().setAttribute("admin", null);

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
        }else if(userService.isUserAdmin()){
        		//es administrador
        	req.getSession().setAttribute("admin", true);
        }
		
		ReporteDAO reporteDao = ReporteDAOImpl.getInstance();
		
		List<Reporte> reportes = reporteDao.listReportes();
		req.getSession().setAttribute("reportes", new ArrayList<Reporte>(reportes));
		
        // COJO LOS 10 PRIMEROS REALIZADOS Y 10 RECIBIDOS PARA LA TABLA EN MAIN
		// MISMO CODIGO QUE EN MISREPORTESSERVLET
		String googleID = userService.getCurrentUser().getUserId();
		UsuarioDAO usuarioDao = UsuarioDAOImpl.getInstance();
		Usuario usuario = usuarioDao.getUsuario(googleID);
		String matricula = null;
		
		if (usuario != null){
			matricula = usuario.getMatricula();
		}
		if(matricula == null || matricula.equals("")){
			req.getSession().setAttribute("mensajeInfo", "No has introducido una matricula en tu cuenta");			
		}else{
			List<Reporte> recibidos = reporteDao.getReportesConMatricula(matricula);
			List<Reporte> realizados = reporteDao.getReportesConGoogleID(googleID);
			recibidos = reducirLista(recibidos);
			realizados = reducirLista(realizados);
			req.getSession().setAttribute("recibidos", new ArrayList<Reporte>(recibidos));
			req.getSession().setAttribute("realizados", new ArrayList<Reporte>(realizados));
		}
		// FIN COPIA
		
		RequestDispatcher view = req.getRequestDispatcher("main.jsp");
        view.forward(req, resp);
	}
	
	public List<Reporte> reducirLista(List<Reporte> l) {
		if (l.size()>6) {
			return l.subList(0, 7);
		} else {
			return l;
		}
	}
}
