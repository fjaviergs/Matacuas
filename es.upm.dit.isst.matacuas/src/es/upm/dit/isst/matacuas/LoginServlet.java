package es.upm.dit.isst.matacuas;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.matacuas.dao.UsuarioDAO;
import es.upm.dit.isst.matacuas.dao.UsuarioDAOImpl;
import es.upm.dit.isst.matacuas.model.Usuario;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
		UserService userService = UserServiceFactory.getUserService();

        String thisURL = req.getRequestURI();

        if (req.getUserPrincipal() != null) {
            String userID = userService.getCurrentUser().getUserId();
            String email = userService.getCurrentUser().getEmail();
            String urlLogOut = userService.createLogoutURL(thisURL);
            
            req.getSession().setAttribute("userId", userID);
            req.getSession().setAttribute("email", email);
            req.getSession().setAttribute("urlLogOut", urlLogOut);
    		
    		RequestDispatcher view = req.getRequestDispatcher("main.jsp");
            view.forward(req, resp);
            
        } else {
            String urlLogIn = userService.createLoginURL(thisURL);
            
            req.getSession().setAttribute("urlLogIn", urlLogIn);
            
            RequestDispatcher view = req.getRequestDispatcher("login.jsp");
            view.forward(req, resp);
        }
    }
}