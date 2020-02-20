package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.User;
import DAO.RegisterDAO;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setUserName(request.getParameter("username"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("pword"));
		String confirmPassword = request.getParameter("pword2");
		if(RegisterDAO.insertUserDAO(request, user, confirmPassword)) {
			RequestDispatcher rd = request.getRequestDispatcher("./View/RegisterSuccess.jsp");
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("./View/Register.jsp");
			rd.forward(request, response);
		}
	}

}
