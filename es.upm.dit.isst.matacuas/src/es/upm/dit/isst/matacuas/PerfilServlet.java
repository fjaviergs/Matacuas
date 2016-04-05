package es.upm.dit.isst.matacuas;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.matacuas.dao.UsuarioDAO;
import es.upm.dit.isst.matacuas.dao.UsuarioDAOImpl;
import es.upm.dit.isst.matacuas.model.Usuario;

public class PerfilServlet extends HttpServlet {
	
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
		 * recuperar datos del form
		 */
		String matricula = quitaNulos(req.getParameter("matricula"));

		/*
		 * Validaciones en servidor
		 */		
		/*if (matricula == ""){
			//matricula en blanco
			req.getSession().setAttribute("mensajeError", "Indroduce la matricula");
			RequestDispatcher view = req.getRequestDispatcher("perfil.jsp");
	        view.forward(req, resp);
	        return;
		}*/
		
		String googleID = userService.getCurrentUser().getUserId();
		UsuarioDAO dao = UsuarioDAOImpl.getInstance();
		/*
		 * Si ya existe el usuario lo actualizo
		 * Si no existe el usuario lo creo
		 */
		if(dao.getUsuario(googleID) == null){
			dao.add(googleID, matricula);
		} else {
		dao.update(googleID, matricula);
		}
		
		/*
		 * aviso de actualización de perfil
		 */
		req.getSession().setAttribute("mensajeInfo", "Perfil actualizado con exito");
		req.getSession().setAttribute("mensajeError", null);
		
		RequestDispatcher view = req.getRequestDispatcher("main.jsp");
        view.forward(req, resp);
	}
	
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
        }
        
        String googleID = userService.getCurrentUser().getUserId();
		UsuarioDAO dao = UsuarioDAOImpl.getInstance();
		Usuario usuario = dao.getUsuario(googleID);
		
		if(usuario !=null){
			req.getSession().setAttribute("miMatricula", usuario.getMatricula());
		}
		
		req.getSession().setAttribute("mensajeInfo", null);
		req.getSession().setAttribute("mensajeError", null);
        
		RequestDispatcher view = req.getRequestDispatcher("perfil.jsp");
        view.forward(req, resp);
		
	}

	private String quitaNulos(String string) {
		if (string == null) {
			return "";
		}
		return string;
	}
}
