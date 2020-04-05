package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.TestToeic;
import BEAN.User;
import DAO.CheckSessionDAO;
import DAO.GetTestToeicDAO;
import DAO.UpdateTestToeicDAO;

@WebServlet("/EditTestToeicController")
public class EditTestToeicController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditTestToeicController() {
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
		User user = CheckSessionDAO.checkSession(request);
		if(user != null && user.getUserTypeID() == 1) {
			String testToeicName = request.getParameter("testToeicName");
			UpdateTestToeicDAO.editTestToeic(request);
			List<TestToeic> listTestToeic = GetTestToeicDAO.getListTestToeicBasedName(request, testToeicName);
			request.setAttribute("testToeicName", testToeicName);
			request.setAttribute("listTestToeic", listTestToeic);
			request.getRequestDispatcher("/View/Admin/UpdateTestToeic.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("/View/Home.jsp").forward(request, response);
		}
	}

}
