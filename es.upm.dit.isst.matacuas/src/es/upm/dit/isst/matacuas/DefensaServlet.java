package es.upm.dit.isst.matacuas;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.matacuas.dao.DefensaDAO;
import es.upm.dit.isst.matacuas.dao.DefensaDAOImpl;
import es.upm.dit.isst.matacuas.dao.ReporteDAO;
import es.upm.dit.isst.matacuas.dao.ReporteDAOImpl;
import es.upm.dit.isst.matacuas.dao.UsuarioDAO;
import es.upm.dit.isst.matacuas.dao.UsuarioDAOImpl;
import es.upm.dit.isst.matacuas.model.Reporte;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class DefensaServlet extends HttpServlet {

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
		String descripcion = quitaNulos(req.getParameter("descripcion"));
		long reporteID = Long.parseLong(req.getParameter("reporteID"));

		/*
		 * Validaciones en servidor
		 */		
		if(descripcion == ""){
			//descripcion en blanco
			req.getSession().setAttribute("mensajeError", "escribe una descripción");
				RequestDispatcher view = req.getRequestDispatcher("respuesta.jsp");
		        view.forward(req, resp);	
	        return;
		}
		
String googleID = userService.getCurrentUser().getUserId();
		
		ReporteDAO reporteDAO = ReporteDAOImpl.getInstance();
		Reporte reporte = reporteDAO.getReporte(reporteID);
		DefensaDAO defensaDAO = DefensaDAOImpl.getInstance();
		defensaDAO.add(reporteID, googleID, descripcion);
		
		/*
		 * envio un aviso por email
		 */
		UsuarioDAO usuarioDAO = UsuarioDAOImpl.getInstance();
		String email = usuarioDAO.getEmailConMatricula(reporte.getMatricula());
		
		if(email != null){
			//si existe un email asociado a la matricula procedo
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		String msgBody = "Descripción de la defensa: " + descripcion;
		String msgSubject = "Se ha recibido una defensa a tu reporte";

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
		req.getSession().setAttribute("mensajeInfo", "Defensa enviada con exito");
		req.getSession().setAttribute("mensajeError", null);
		
		RequestDispatcher view = req.getRequestDispatcher("main.jsp");
        view.forward(req, resp);
	}

	private String quitaNulos(String string) {
		if (string == null) {
			return "";
		}
		return string;
	}
} 