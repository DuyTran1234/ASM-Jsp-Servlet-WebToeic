package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.ListeningExercise;
import BEAN.User;
import DAO.DeleteListeningExerciseDAO;
import DAO.GetListeningExerciseDAO;

@WebServlet("/DeleteListeningExerciseController")
public class DeleteListeningExerciseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteListeningExerciseController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
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
			
			if(request.getParameter("DeleteAll").equals("true")) {			
				DeleteListeningExerciseDAO.deleteAllListeningExercise(exerciseName, request);
			}
			else {
				int exerciseID = Integer.parseInt(request.getParameter("exerciseID"));
				DeleteListeningExerciseDAO.deleteListeningExerciseBasedId(exerciseID, request);
			}
			
			ArrayList<ListeningExercise> list = GetListeningExerciseDAO.getExerciseBasedName(exerciseName);
			request.setAttribute("listeningExerciseName", exerciseName);
			request.setAttribute("listListeningExercise", list);
			
			request.getRequestDispatcher("/View/Admin/DeleteListeningExercise.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("/View/Home.jsp").forward(request, response);
		}
	}

}
