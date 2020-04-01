package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.User;
import DAO.CheckTestToeicNameDAO;

@WebServlet("/CheckTestToeicNameAjax")
public class CheckTestToeicNameAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckTestToeicNameAjax() {
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
			String testToeicName = request.getParameter("testToeicName");
			if(!CheckTestToeicNameDAO.checkTestToeicName(testToeicName, request)) {
				request.setAttribute("msgCheckToeicName", "Tên hợp lệ");
			}
			else {
				request.setAttribute("msgCheckToeicName", "Tồn tại tên đề thi trong cơ sở dữ liệu");
			}
			request.getRequestDispatcher("/View/Admin/CheckTestToeicName.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("/View/Home.jsp").forward(request, response);
		}
	}

}
