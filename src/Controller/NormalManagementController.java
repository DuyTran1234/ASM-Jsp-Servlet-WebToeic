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

@WebServlet("/NormalManagementController")
public class NormalManagementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NormalManagementController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		
		HttpSession session = request.getSession(false);
		
		user = (User)session.getAttribute("sessionUser");
		
		user.setFullName(request.getParameter("fullname"));
		user.setPhoneNumber(request.getParameter("phone-number"));
		user.setAddress(request.getParameter("address"));
		
		UpdateUserDAO.updateUser(user);
		user = SelectUserDAO.selectUser(user);
		
		session.setAttribute("sessionUser", user);
		request.setAttribute("msgUpdate", "Update thành công");
		RequestDispatcher rd = request.getRequestDispatcher("./View/NormalManagement.jsp");
		rd.forward(request, response);
	}

}
