package es.upm.dit.isst.matacuas;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.datanucleus.util.Base64;

import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.matacuas.dao.ReporteDAO;
import es.upm.dit.isst.matacuas.dao.ReporteDAOImpl;
import es.upm.dit.isst.matacuas.dao.UsuarioDAO;
import es.upm.dit.isst.matacuas.dao.UsuarioDAOImpl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class ReporteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String emailAdmin = "matacuasg21@gmail.com";

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
		String descripcion = quitaNulos(req.getParameter("descripcion"));
		String lugar = quitaNulos(req.getParameter("lugar"));
		String imagen = quitaNulos(req.getParameter("pic"));
		System.out.println(req.getParameter("pic"));
		/*
		 * Recupero la imagen y la almaceno como byte[]
		 * falta testear
		 */
		Text imagenB64;
		if (imagen == null || imagen.equals("")){
			imagenB64 = null;	
		} else {
			imagenB64 = new Text(imagen);
		}
		
		Boolean esPositivo;
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
		
String googleID = userService.getCurrentUser().getUserId();
		
		ReporteDAO reporteDAO = ReporteDAOImpl.getInstance();
			System.out.println("con foto");
		reporteDAO.add(googleID, matricula, descripcion, lugar, imagenB64, esPositivo);
		
		/*
		 * envio un aviso por email
		 */
		UsuarioDAO usuarioDAO = UsuarioDAOImpl.getInstance();
		String email = usuarioDAO.getEmailConMatricula(matricula);
		
		if(email != null){
			//si existe un email asociado a la matricula procedo
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		String msgBody = "Descripción del reporte: " + descripcion;
		String msgSubject = "Has recibido un reporte";
		if (esPositivo = true){msgSubject = msgSubject + " positivo";}
		else{msgSubject = msgSubject + " negativo";}

		try {
		    Message msg = new MimeMessage(session);
		    msg.setFrom(new InternetAddress(emailAdmin, "Matacuas"));
		    msg.addRecipient(Message.RecipientType.TO,
		     new InternetAddress(email));
		    msg.setSubject(msgSubject);
		    msg.setText(msgBody);
		    Transport.send(msg);

		} catch (AddressException e) {
		    // ...
		} catch (MessagingException e) {
		    // ...
		}
	}
		

		/*
		 * Puede mejorarse para dar distinto mensaje segun sea reporte positivo o negativo
		 */
		req.getSession().setAttribute("mensajeInfo", "Reporte creado con exito");
		req.getSession().setAttribute("mensajeError", null);
		
		RequestDispatcher view = req.getRequestDispatcher("redirect.jsp");
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
		
		req.getSession().setAttribute("mensajeInfo", null);
		req.getSession().setAttribute("mensajeError", null);

		RequestDispatcher view = req.getRequestDispatcher("matacuas.jsp");
        view.forward(req, resp);
		
	}


	private String quitaNulos(String string) {
		if (string == null) {
			return "";
		}
		return string;
	}
} 