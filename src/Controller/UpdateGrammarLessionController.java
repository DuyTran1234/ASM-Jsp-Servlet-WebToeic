package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.Lession;
import BEAN.User;
import DAO.GetGrammarLessionDAO;
import DAO.UpdateLessionGrammarDAO;

@WebServlet("/UpdateGrammarLessionController")
public class UpdateGrammarLessionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateGrammarLessionController() {
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
		if(user == null) {
			RequestDispatcher rd = request.getRequestDispatcher("./View/Login.jsp");
			rd.forward(request, response);
		}
		else {
			Lession lessionNew = new Lession();
			lessionNew = UpdateLessionGrammarDAO.getData(request, lessionNew);
			if(lessionNew.getContent() != null) {
				UpdateLessionGrammarDAO.updateLessionGrammar(lessionNew, lessionNew.getLessionNameOld());
				String listJSON = GetGrammarLessionDAO.getLession();
    			request.setAttribute("listJSON", listJSON);
    			request.setAttribute("msgUpdate", "Update thành công");
			}
			else if(lessionNew.getContent() == null && lessionNew.getLessionName() != null) {
				UpdateLessionGrammarDAO.updateLessionGrammar(lessionNew.getLessionName(), lessionNew.getLessionNameOld());
				String listJSON = GetGrammarLessionDAO.getLession();
    			request.setAttribute("listJSON", listJSON);
    			request.setAttribute("msgUpdate", "Update thành công");
			}
			else {
				String listJSON = GetGrammarLessionDAO.getLession();
    			request.setAttribute("listJSON", listJSON);
    			request.setAttribute("msgUpdate", "Update thất bại");
			}
			request.setAttribute("msgUploadFile", null);
			RequestDispatcher rd = request.getRequestDispatcher("./View/Admin/GrammarLessionManagement.jsp");
			rd.forward(request, response);
		}
	}

}
