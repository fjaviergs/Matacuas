package es.upm.dit.isst.matacuas;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.matacuas.dao.UsuarioDAO;
import es.upm.dit.isst.matacuas.dao.UsuarioDAOImpl;
import es.upm.dit.isst.matacuas.model.Usuario;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		String nusuario = quitaNulos(req.getParameter("nusuario"));
		String password = quitaNulos(req.getParameter("password"));

		/*
		 * Validaciones en servidor
		 */		
		if (nusuario == ""){
			//usuario en blanco
			req.getSession().setAttribute("mensajeError", "Indroduce tu nombre de usuario");
			RequestDispatcher view = req.getRequestDispatcher("login.jsp");
	        view.forward(req, resp);
	        return;
		}

		if(password == ""){
			//contraseña en blanco
			req.getSession().setAttribute("mensajeError", "Introduce tu contraseña");
			RequestDispatcher view = req.getRequestDispatcher("login.jsp");
	        view.forward(req, resp);
	        return;
		}
		
		/*
		 * Compruebo si el usuario y contraseña son correctos
		 */
		UsuarioDAO dao = UsuarioDAOImpl.getInstance();
		Usuario usuario = dao.getUsuario(nusuario, password);
		
		if(usuario == null){
			//login incorrecto
			req.getSession().setAttribute("mensajeError", "Usuario o contraseña incorrectos");
			RequestDispatcher view = req.getRequestDispatcher("login.jsp");
	        view.forward(req, resp);
	        return;
		}
		
		/*
		 * Se crea sesión
		 * Puede que se añadan más parametros
		 */
		req.getSession().setAttribute("nUsuario", usuario.getNUsuario());
		
		req.getSession().setAttribute("mensajeInfo", "Logueado con exito");
		
		RequestDispatcher view = req.getRequestDispatcher("main.jsp");
        view.forward(req, resp);
	}
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		RequestDispatcher view = req.getRequestDispatcher("login.jsp");
        view.forward(req, resp);
		
	}

	private String quitaNulos(String string) {
		if (string == null) {
			return "";
		}
		return string;
	}
}