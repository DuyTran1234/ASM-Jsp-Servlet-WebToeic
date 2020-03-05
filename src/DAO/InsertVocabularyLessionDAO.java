package DAO;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import BEAN.Lession;
import DB.Connect;

public class InsertVocabularyLessionDAO {
	public static List<Lession> getLession(HttpServletRequest request) {
		
		ArrayList<String> listContent = new ArrayList<>();
		ArrayList<String> listLessionName = new ArrayList<>();
		
		// create repository virtual save file upload, use folder tmp of Windows from to System.getProperty("java.io.tmpdir")
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(2 * 1024 * 1024);	// Set size file upload is 2 MB
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		
		ServletFileUpload upload = new ServletFileUpload(factory);	// get form save from folder virtual
		upload.setSizeMax(50 * 1024 *1024);	// Set size form get to request client is 50 MB
		
		try {
			List<FileItem> items = upload.parseRequest(request); // get form and file from request, pass list object FileItem
			Iterator<FileItem> iterator = items.iterator();
			while(iterator.hasNext()) {
				FileItem item = iterator.next();
				// read form field, get field name "lessionID[index]" from jsp to listLessionName
				if(item.isFormField()) {
					String lessionName = "";
					try {
						lessionName = item.getString("UTF-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(checkLessionNameDuplicationInDatabase(lessionName, request)) {
						request.setAttribute("msgInsertVocabulary", "Tồn tại lession name trong cơ sở dữ liệu");
						return null;
					}
					String sizePresent = "lessionID[" + listLessionName.size() + "]";
					if(item.getFieldName().equals(sizePresent)) {
						listLessionName.add(item.getString());
					}
				}
				// read file upload, pass File to method getContent() get content File .docx
				else {
					if(!checkPatternFileName(item.getName())) {
						request.setAttribute("msgInsertVocabulary", "File đã chọn không có định dạng .docx");
						return null;
					}
					File uploadFile = new File("E:" + File.separator + "TestUploadFile" + item.getName());
					try {
						item.write(uploadFile);
					} catch (Exception e) {
						request.setAttribute("msgInsertVocabulary", "Không thể tiến hành ghi File");
					}
				}
			}
		} catch (FileUploadException e) {
			request.setAttribute("msgInsertVocabulary", "Không tìm thấy dữ liệu để xử lý");
			return null;
		}	
	}
	
	private static boolean checkPatternFileName(String fileName) {
		Pattern pattern = Pattern.compile(".+\\.docx");
		Matcher matcher = pattern.matcher(fileName);
		return matcher.matches();
	}
	
	private static boolean checkLessionNameDuplicationInDatabase(String lessionName, HttpServletRequest request) {
		String sql = "select * from vocabulary_lession where lessionName = ?";
		try {
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			if(!result.next()) {
				return true;
			}
		} catch (SQLException e) {
			request.setAttribute("msgInsertVocabulary", "Không thể kết nối đến database (1)");
		}
		return false;
	}
}
