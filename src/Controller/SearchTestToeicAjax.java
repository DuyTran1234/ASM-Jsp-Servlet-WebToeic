package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.User;
import DAO.CheckSessionDAO;
import DAO.GetTestToeicDAO;

@WebServlet("/SearchTestToeicAjax")
public class SearchTestToeicAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchTestToeicAjax() {
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
			List<String> listName = GetTestToeicDAO.getListSearchNameTestToeic(request, testToeicName);
			if(listName.size() > 0) {
				request.setAttribute("listNameTestToeic", listName);
			}
			else {
				request.setAttribute("listNameTestToeic", null);
			}
			request.getRequestDispatcher("/View/Admin/SearchTestToeicAjax.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("/View/Home.jsp").forward(request, response);
		}
	}

}
