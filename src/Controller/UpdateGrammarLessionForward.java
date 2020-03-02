package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Lession;
import DAO.GetGrammarLessionDAO;

@WebServlet("/UpdateGrammarLessionForward")
public class UpdateGrammarLessionForward extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateGrammarLessionForward() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Lession> list = new ArrayList<>();
		list = GetGrammarLessionDAO.getLession(list);
		String lessionNameUpdate = request.getParameter("lessionName");
		for(int i = 0; i < list.size(); i++) {
			if(lessionNameUpdate.equals(list.get(i).getLessionName())) {
				request.setAttribute("lessionUpdate", list.get(i));
				break;
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("./View/Admin/UpdateGrammarLessionAjax.jsp");
		rd.forward(request, response);
	}

}
