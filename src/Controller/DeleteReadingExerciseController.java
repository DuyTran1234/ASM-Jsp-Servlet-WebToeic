package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.User;
import DAO.GetReadingExerciseDAO;
import DAO.UpdateReadingExerciseDAO;

@WebServlet("/DeleteReadingExerciseController")
public class DeleteReadingExerciseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteReadingExerciseController() {
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
			String exerciseName = request.getParameter("exerciseNameDelete");
			if(UpdateReadingExerciseDAO.deleteExercise(exerciseName)) {
				request.setAttribute("msgDeleteReading", "Xoá bài tập thành công");
			}
			else {
				request.setAttribute("msgDeleteReading", "Xoá bài tập thất bại");
			}
			String valueJSON = GetReadingExerciseDAO.getValueJSON();
			String nameJSON = GetReadingExerciseDAO.getNameJSON();
			request.setAttribute("listJSON-value", valueJSON);
			request.setAttribute("listJSON-name", nameJSON);
			request.getRequestDispatcher("/View/Admin/ReadingExerciseManagement.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("/View/Login.jsp").forward(request, response);
		}
	}

}
