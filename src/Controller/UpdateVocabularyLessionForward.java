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
import DAO.UpdateVocabularyLessionDAO;

@WebServlet("/UpdateVocabularyLessionForward")
public class UpdateVocabularyLessionForward extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateVocabularyLessionForward() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession(false);
		User user = new User();
		user = (User)session.getAttribute("sessionUser");
		if(user != null) {
			String lessionNameOld = request.getParameter("lessionName");
			if(UpdateVocabularyLessionDAO.checkExistsLessionName(lessionNameOld)) {
				request.setAttribute("lessionNameOld", lessionNameOld);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/View/Admin/UpdateVocabularyLessionAjax.jsp");
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/View/Login.jsp");
			rd.forward(request, response);
		}
	}

}
