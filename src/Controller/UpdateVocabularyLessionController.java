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
import DAO.GetVocabularyLessionDAO;
import DAO.UpdateVocabularyLessionDAO;

@WebServlet("/UpdateVocabularyLessionController")
public class UpdateVocabularyLessionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateVocabularyLessionController() {
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
			if(UpdateVocabularyLessionDAO.updateVocabularyLession(request)) {
				request.setAttribute("msgUpdateVocabulary", "Update thành công");
			}
			else {
				request.setAttribute("msgUpdateVocabulary", "Update thất bại");
			}
			String listJSON = GetVocabularyLessionDAO.getLession();
			request.setAttribute("listJSON", listJSON);
			RequestDispatcher rd = request.getRequestDispatcher("/View/Admin/VocabularyLessionManagement.jsp");
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/View/Login.jsp");
			rd.forward(request, response);
		}
	}

}
