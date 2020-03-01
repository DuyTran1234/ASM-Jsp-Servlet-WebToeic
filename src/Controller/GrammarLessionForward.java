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
import DAO.GetGrammarLessionDAO;

@WebServlet("/GrammarLessionForward")
public class GrammarLessionForward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GrammarLessionForward() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User user = (User)session.getAttribute("sessionUser");
		String listJSON = GetGrammarLessionDAO.getLession();
		if(user != null) {
			if(user.getUserTypeID() == 1) {
				request.setAttribute("listJSON", listJSON);
				RequestDispatcher rd = request.getRequestDispatcher("./View/Admin/GrammarLessionManagement.jsp");
				rd.forward(request, response);
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("./View/Home.jsp");
				rd.forward(request, response);
			}
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("./View/Login.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
