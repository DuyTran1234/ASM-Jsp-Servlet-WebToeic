package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import BEAN.ListeningExercise;
import BEAN.User;
import DAO.GetListeningExerciseDAO;

@WebServlet("/DisplayListeningExerciseForward")
public class DisplayListeningExerciseForward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DisplayListeningExerciseForward() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User user = new User();
		user = (User)session.getAttribute("sessionUser");
		if(user != null) {
			List<ListeningExercise> list = GetListeningExerciseDAO.getAllListeningExercise();
			if(list != null) {			
				String JSON = new Gson().toJson(list);
				request.setAttribute("list-listening-exercise", JSON);
				request.getRequestDispatcher("/View/Admin/DisplayListeningExercise.jsp").forward(request, response);
			}
			else {
				request.setAttribute("msgError", "Lỗi kết nối cơ sở dữ liệu");
				request.getRequestDispatcher("/View/Admin/ListeningExerciseManagement.jsp").forward(request, response);
			}
		}
		else {
			request.getRequestDispatcher("/View/Home.jsp").forward(request, response);
		}
	}

}
