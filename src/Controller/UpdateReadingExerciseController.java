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

@WebServlet("/UpdateReadingExerciseController")
public class UpdateReadingExerciseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateReadingExerciseController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		User user = new User();
		HttpSession session = request.getSession(false);
		user = (User)session.getAttribute("sessionUser");
		if(user != null && user.getUserTypeID() == 1) {
			UpdateReadingExerciseDAO.updateReadingExercise(request);
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
