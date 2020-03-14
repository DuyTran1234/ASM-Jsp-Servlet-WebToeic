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
import DAO.GetReadingExerciseDAO;

@WebServlet("/ReadingExerciseForward")
public class ReadingExerciseForward extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ReadingExerciseForward() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User user = new User();
		user = (User)session.getAttribute("sessionUser");
		if(user != null && user.getUserTypeID() == 1) {
			String valueJSON = GetReadingExerciseDAO.getValueJSON();
			String nameJSON = GetReadingExerciseDAO.getNameJSON();
			request.setAttribute("listJSON-value", valueJSON);
			request.setAttribute("listJSON-name", nameJSON);
			RequestDispatcher rd = request.getRequestDispatcher("/View/Admin/ReadingExerciseManagement.jsp");
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/View/Home.jsp");
			rd.forward(request, response);
		}
	}

}
