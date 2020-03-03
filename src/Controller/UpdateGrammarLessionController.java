package Controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.Lession;
import BEAN.User;
import DAO.AddFileGrammarLession;
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
			Lession lession = new Lession();
			lession = 
			
			String lessionNameOld = lession.getLessionName();
			
//			Lession lessionNew = new Lession();
//			List<String> listLessionName = new ArrayList<>();
//			List<File> listFile = AddFileGrammarLession.addFile(request, listLessionName);
//			if(listFile != null) {
//				for(int i = 0; i < listFile.size(); i++) {
//					lessionNew.setLessionName(listLessionName.get(i));
//					lessionNew.setContent(AddFileGrammarLession.getContentFile(listFile.get(i)));
//					if(UpdateLessionGrammarDAO.updateLessionGrammar(lessionNew, lessionOld.getLessionName())) {
//						String listJSON = GetGrammarLessionDAO.getLession();
//	        			request.setAttribute("listJSON", listJSON);
//	        			request.setAttribute("msgUploadFile", null);
//						request.setAttribute("msgUpdate", "Update thành công");
//					}
//					else {
//						String listJSON = GetGrammarLessionDAO.getLession();
//	        			request.setAttribute("listJSON", listJSON);
//						request.setAttribute("msgUpdate", "Update thất bại");
//					}
//				}
//			}
//			else if(listFile == null && listLessionName != null) {
//				for(int i = 0; i < listLessionName.size(); i++) {
//					if(UpdateLessionGrammarDAO.updateLessionGrammar(listLessionName.get(i), lessionOld.getLessionName())) {
//						String listJSON = GetGrammarLessionDAO.getLession();
//	        			request.setAttribute("listJSON", listJSON);
//						request.setAttribute("msgUpdate", "Update thành công");
//					}
//					else {					
//						String listJSON = GetGrammarLessionDAO.getLession();
//	        			request.setAttribute("listJSON", listJSON);
//						request.setAttribute("msgUpdate", "Update thất bại");
//					}
//				}
//			}
			request.setAttribute("msgUpdate", lessionNameOld);
			RequestDispatcher rd = request.getRequestDispatcher("./View/Admin/GrammarLessionManagement.jsp");
			rd.forward(request, response);
		}
	}

}
