package Controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Lession;
import DAO.AddFileGrammarLession;

@WebServlet("/UploadGrammarLession")
public class UploadGrammarLession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UploadGrammarLession() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
        List<String> listLessionName = new ArrayList<>();
        List<File> listFile = AddFileGrammarLession.addFile(request, listLessionName);
        String test = "";
        if(listFile != null) {
        	for(int i = 0; i < listFile.size(); i++) {
        		Lession lession = new Lession();
        		lession.setLessionName(listLessionName.get(i));
        		lession.setContent(AddFileGrammarLession.getContentFile(listFile.get(i)));
        		lession.setDateToday(new Date());
        		if(AddFileGrammarLession.insertLession(lession)) {
        			request.setAttribute("msgUploadFile", "Upload thành công");
        		}
        		else {
        			request.setAttribute("msgUploadFile", "Upload thất bại");
        		}
        	}
        	request.setAttribute("Content", test);
        }
        RequestDispatcher rd = request.getRequestDispatcher("/View/Admin/GrammarLessionManagement.jsp");
        rd.forward(request, response);
	}

}
