package es.upm.dit.isst.matacuas;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.matacuas.dao.ReporteDAO;
import es.upm.dit.isst.matacuas.dao.ReporteDAOImpl;

public class ReporteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		/*
		 * recuperar datos del form
		 * falta almacenar la foto (cambiar base de datos de reportes)
		 */		
		String matricula = quitaNulos(req.getParameter("matricula"));
		String descripcion = quitaNulos(req.getParameter("descripcion"));
		String lugar = quitaNulos(req.getParameter("lugar"));
		Boolean esPositivo;
	//	String pic = quitaNulos(req.getParameter("pic"));
		if (req.getParameter("positivo").equals("true")){
			esPositivo=true;
		}else{
			esPositivo=false;
		}

		/*
		 * Validaciones en servidor
		 */		
		if (matricula == ""){
			//matricula en blanco en blanco
			req.getSession().setAttribute("mensajeError", "Indroduce la matricula");
			if(esPositivo){
				RequestDispatcher view = req.getRequestDispatcher("premiar.jsp");
		        view.forward(req, resp);	
			}else{
			RequestDispatcher view = req.getRequestDispatcher("matacuas.jsp");
	        view.forward(req, resp);
			}
	        return;
		}

		if(descripcion == ""){
			//descripcion en blanco
			req.getSession().setAttribute("mensajeError", "escribe una descripción");
			if(esPositivo){
				RequestDispatcher view = req.getRequestDispatcher("premiar.jsp");
		        view.forward(req, resp);	
			}else{
			RequestDispatcher view = req.getRequestDispatcher("matacuas.jsp");
	        view.forward(req, resp);
			}
	        return;
		}
		
		if(lugar==""){
			//lugar en blanco
			req.getSession().setAttribute("mensajeError", "indica el lugar");
			if(esPositivo){
				RequestDispatcher view = req.getRequestDispatcher("premiar.jsp");
		        view.forward(req, resp);	
			}else{
			RequestDispatcher view = req.getRequestDispatcher("matacuas.jsp");
	        view.forward(req, resp);
			}
	        return;
		}
		
		ReporteDAO dao = ReporteDAOImpl.getInstance();
		dao.add(matricula, descripcion, lugar, esPositivo);
		

		/*
		 * Puede mejorarse para dar distinto mensaje segun sea reporte positivo o negativo
		 */
		req.getSession().setAttribute("mensajeInfo", "Reporte creado con exito");
		req.getSession().setAttribute("mensajeError", null);
		
		RequestDispatcher view = req.getRequestDispatcher("main.jsp");
        view.forward(req, resp);
	}
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		req.getSession().setAttribute("mensajeInfo", null);
		req.getSession().setAttribute("mensajeError", null);

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