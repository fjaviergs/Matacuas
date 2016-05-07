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

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
		
		req.getSession().setAttribute("mensajeInfo", null);
		req.getSession().setAttribute("mensajeError", null);
		
		UserService userService = UserServiceFactory.getUserService();

        String thisURL = req.getRequestURI();

        if (req.getUserPrincipal() != null) {
            String googleID = userService.getCurrentUser().getUserId();
            String email = userService.getCurrentUser().getEmail();
            String urlLogOut = userService.createLogoutURL(thisURL);
            
            /*
             * Compruebo si existe el usuario en la base de datos
             * si no existe lo almaceno
             */           
            UsuarioDAO dao = UsuarioDAOImpl.getInstance();
    		if(dao.getUsuario(googleID) == null){
    			dao.add(googleID, email, "");
    			if (userService.isUserAdmin()){
    				//es administrador
    				req.getSession().setAttribute("admin", true);
    			}
    		} 
            
            req.getSession().setAttribute("googleID", googleID);
            req.getSession().setAttribute("email", email);
            req.getSession().setAttribute("urlLogOut", urlLogOut);
    		
    		RequestDispatcher view = req.getRequestDispatcher("redirect.jsp");
            view.forward(req, resp);
            
        } else {
            String urlLogIn = userService.createLoginURL(thisURL);
            
            req.getSession().setAttribute("urlLogIn", urlLogIn);
            
            RequestDispatcher view = req.getRequestDispatcher("login.jsp");
            view.forward(req, resp);
        }
    }
}