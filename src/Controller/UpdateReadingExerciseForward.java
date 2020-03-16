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
import DAO.UpdateReadingExerciseDAO;

@WebServlet("/UpdateReadingExerciseForward")
public class UpdateReadingExerciseForward extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public UpdateReadingExerciseForward() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		User user = new User();
		HttpSession session = request.getSession(false);
		user = (User)session.getAttribute("sessionUser");
		if(user != null && user.getUserTypeID() == 1) {
			String exerciseNameOld = request.getParameter("exerciseNameOld");
			if(UpdateReadingExerciseDAO.checkExistsExercise(exerciseNameOld)) {
				request.setAttribute("exercise-name-old", exerciseNameOld);
			}
			else {
				request.setAttribute("exercise-name-old", null);
			}
			request.getRequestDispatcher("/View/Admin/UpdateReadingExerciseAJAX.jsp").forward(request, response);;		
		}
		else {
			request.getRequestDispatcher("/View/Login.jsp").forward(request, response);;
		}
	}

}
