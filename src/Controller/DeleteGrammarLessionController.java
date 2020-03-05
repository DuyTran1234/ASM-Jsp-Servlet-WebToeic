package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DeleteLessionGrammarDAO;
import DAO.GetGrammarLessionDAO;

@WebServlet("/DeleteGrammarLessionController")
public class DeleteGrammarLessionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteGrammarLessionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    String deleteLessionName = request.getParameter("lession-name-delete");
	    if(DeleteLessionGrammarDAO.deleteLession(deleteLessionName)) {
	    	request.setAttribute("msgDelete", "Delete thành công");
	    }
	    else {
	    	request.setAttribute("msgDelete", "Delete thất bại, kiểm tra tên bài cần xoá");
	    }
	    String listJSON = GetGrammarLessionDAO.getLession();
		request.setAttribute("listJSON", listJSON);
	    RequestDispatcher rd = request.getRequestDispatcher("./View/Admin/GrammarLessionManagement.jsp");
	    rd.forward(request, response);
	}

}
