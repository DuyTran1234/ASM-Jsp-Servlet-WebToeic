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
import DAO.DeleteVocabularyLessionDAO;
import DAO.GetVocabularyLessionDAO;
import DAO.UpdateVocabularyLessionDAO;

@WebServlet("/DeleteVocabularyLessionController")
public class DeleteVocabularyLessionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteVocabularyLessionController() {
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
		HttpSession session = request.getSession(false);
		User user = new User();
		user = (User)session.getAttribute("sessionUser");
		if(user != null) {
			String lessionName = request.getParameter("lessionName");
			if(UpdateVocabularyLessionDAO.checkLessionNameInDatabase(lessionName)) {
				if(DeleteVocabularyLessionDAO.deleteLession(lessionName)) {
					request.setAttribute("msgDeleteVocabulary", "Xoá bài học thành công");
				}
				else {
					request.setAttribute("msgDeleteVocabulary", "Xoá bài học thất bại");
				}
			}
			else {
				request.setAttribute("msgDeleteVocabulary", "Không tồn tại tên bài học");
			}
			String listJSON = GetVocabularyLessionDAO.getLession();
			request.setAttribute("listJSON", listJSON);
			RequestDispatcher rd = request.getRequestDispatcher("/View/Admin/DeleteVocabularyLessionAjax.jsp");
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/View/Login.jsp");
			rd.forward(request, response);
		}
	}

}
