package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.User;
import DAO.CreateListeningExerciseDAO;

@WebServlet("/CreateListeningExerciseForward")
public class CreateListeningExerciseForward extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CreateListeningExerciseForward() {
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
			String exerciseName = request.getParameter("exerciseName");
			if(!CreateListeningExerciseDAO.checkExistsExercise(exerciseName)) {
				request.setAttribute("exercise-name", exerciseName);
			}
			else {
				request.setAttribute("exercise-name", null);
			}
			request.getRequestDispatcher("/View/Admin/CreateListeningExerciseAJAX.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("/View/Home.jsp").forward(request, response);
		}
	}

}
