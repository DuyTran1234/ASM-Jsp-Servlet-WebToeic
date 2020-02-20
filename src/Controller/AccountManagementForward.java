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

@WebServlet("/AccountManagementForward")
public class AccountManagementForward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AccountManagementForward() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		HttpSession session = request.getSession(false);
		user = (User)session.getAttribute("sessionUser");

		if(user.getUserTypeID() == 2) {
			RequestDispatcher rd = request.getRequestDispatcher("./View/NormalManagement.jsp");
			rd.forward(request, response);
		}
		else if(user.getUserTypeID() == 1) {
			RequestDispatcher rd = request.getRequestDispatcher("./View/Home.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
