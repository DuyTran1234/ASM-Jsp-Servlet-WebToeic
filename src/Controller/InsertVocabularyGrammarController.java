package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.GetGrammarLessionDAO;
import DAO.GetVocabularyLessionDAO;
import DAO.InsertVocabularyLessionDAO;

@WebServlet("/InsertVocabularyGrammarController")
public class InsertVocabularyGrammarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertVocabularyGrammarController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
		if(InsertVocabularyLessionDAO.insertVocabularyLession(request)) {
			request.setAttribute("msgInsertVocabulary", "Thêm bài học thành công");
		}
		String listJSON = GetVocabularyLessionDAO.getLession();
		if(listJSON != null) {
			request.setAttribute("listJSON", listJSON);
		}
		RequestDispatcher rd = request.getRequestDispatcher("./View/Admin/VocabularyLessionManagement.jsp");
		rd.forward(request, response);
	}

}
