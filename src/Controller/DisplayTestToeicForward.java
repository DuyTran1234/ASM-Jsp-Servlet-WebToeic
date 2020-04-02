package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import BEAN.TestToeic;
import BEAN.User;
import DAO.CheckSessionDAO;
import DAO.DisplayTestToeicDAO;

@WebServlet("/DisplayTestToeicForward")
public class DisplayTestToeicForward extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public DisplayTestToeicForward() {
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
			List<TestToeic> list = DisplayTestToeicDAO.getList(request);
			if(list != null) {
				String listTestToeicJSON = new Gson().toJson(list);
				request.setAttribute("list-test-toeic-JSON", listTestToeicJSON);
			}
			else {
				request.setAttribute("msgDisplay", "Chưa có dữ liệu");
			}
			request.getRequestDispatcher("/View/Admin/DisplayTestToeic.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("/View/Home.jsp").forward(request, response);
		}
	}

}
