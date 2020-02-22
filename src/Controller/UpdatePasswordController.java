package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.User;
import DAO.SelectUserDAO;
import DAO.UpdateUserDAO;

@WebServlet("/UpdatePasswordController")
public class UpdatePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdatePasswordController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String presentPassword = request.getParameter("present-password");
		String newPassword = request.getParameter("new-password");
		String confirmPassword = request.getParameter("confirm-password");
		HttpSession session = request.getSession(false);
		User user = (User)session.getAttribute("sessionUser");
		UpdateUserDAO.updatePassword(user, newPassword, presentPassword, confirmPassword, request);
		user = SelectUserDAO.selectUser(user);
		session.setAttribute("sessionUser", user);
		RequestDispatcher rd = request.getRequestDispatcher("./View/NormalManagement.jsp");
		rd.forward(request, response);
	}

}
