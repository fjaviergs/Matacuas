package es.upm.dit.isst.matacuas;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.matacuas.dao.UsuarioDAO;
import es.upm.dit.isst.matacuas.dao.UsuarioDAOImpl;

public class RegistroServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		/*
		 * recuperar datos del form
		 */
		
		String nombre = quitaNulos(req.getParameter("nombre"));
		String apellidos = quitaNulos(req.getParameter("apellidos"));
		String nusuario = quitaNulos(req.getParameter("nusuario"));
		String password = quitaNulos(req.getParameter("password"));
		String password2 = quitaNulos(req.getParameter("password2"));
		String matricula = quitaNulos(req.getParameter("matricula"));

		/*
		 * Validaciones en servidor
		 */
		
		if (nusuario == ""){
			//usuario en blanco
			req.getSession().setAttribute("mensajeError", "Indroduce tu nombre de usuario");
			RequestDispatcher view = req.getRequestDispatcher("registro.jsp");
	        view.forward(req, resp);
	        return;
		}

		if(password == ""){
			//contraseña en blanco
			req.getSession().setAttribute("mensajeError", "Introduce una contraseña");
			RequestDispatcher view = req.getRequestDispatcher("registro.jsp");
	        view.forward(req, resp);
	        return;
		}
		
		if(!password.equals(password2)){
			//las contraseñas no coinciden
			req.getSession().setAttribute("mensajeError", "Las contraseñas no coinciden");
			RequestDispatcher view = req.getRequestDispatcher("registro.jsp");
	        view.forward(req, resp);
	        return;
		}
		
		UsuarioDAO dao = UsuarioDAOImpl.getInstance();
		dao.add(nusuario, nombre, apellidos,
				password, matricula);
		
		/*
		 * Se crea sesión
		 * Puede que se añadan más parametros
		 */
		req.getSession().setAttribute("nUsuario", nusuario);
		
		req.getSession().setAttribute("mensajeInfo", "Registro de usuario completado");
		
		RequestDispatcher view = req.getRequestDispatcher("main.jsp");
        view.forward(req, resp);
	}
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		RequestDispatcher view = req.getRequestDispatcher("registro.jsp");
        view.forward(req, resp);
		
	}


	private String quitaNulos(String string) {
		if (string == null) {
			return "";
		}
		return string;
	}
} 