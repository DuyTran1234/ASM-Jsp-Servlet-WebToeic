package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import BEAN.Lession;
import DB.Connect;

public class InsertVocabularyLessionDAO {
	public static List<Lession> getLession(HttpServletRequest request) {
		
		ArrayList<Lession> listLession = new ArrayList<>();
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
					Pattern pattern = Pattern.compile("lessionID.+");
					Matcher matcher = pattern.matcher(item.getFieldName());
					if(matcher.matches()) {
						String lessionName = "";
						try {
							lessionName = item.getString("UTF-8");
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(checkLessionNameDuplicationInDatabase(lessionName, request)) {
							request.setAttribute("msgInsertVocabulary", "Tồn tại tên bài học trong cơ sở dữ liệu");
							return null;
						}						
						listLessionName.add(lessionName);
					}
				}
				// read file upload, pass File to method getContent() get content File .docx
				else {
					if(!checkPatternFileName(item.getName())) {
						request.setAttribute("msgInsertVocabulary", "File đã chọn không có định dạng .docx");
						return null;
					}
					File uploadFile = new File("E:" + File.separator + "TestUploadFile" + File.separator + item.getName());
					try {
						item.write(uploadFile);
						listContent.add(getContent(uploadFile));
						uploadFile.delete();
					} catch (Exception e) {
						request.setAttribute("msgInsertVocabulary", "Không thể tiến hành ghi File");
					}
				}
			}
		} catch (FileUploadException e) {
			request.setAttribute("msgInsertVocabulary", "Không tìm thấy dữ liệu để xử lý");
			return null;
		}
		if(!checkLessionNameDuplicationInForm(listLessionName)) {
			request.setAttribute("msgInsertVocabulary", "Tên bài học trong các biểu mẫu bị trùng, kiểm tra lại");
			return null;
		}
		for(int i = 0; i < listLessionName.size(); i++) {
			Lession lession = new Lession();
			lession.setLessionName(listLessionName.get(i));
			lession.setContent(listContent.get(i));
			lession.setDateToday(new Date());
			listLession.add(lession);
		}
		return listLession;
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
			statement.setString(1, lessionName);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				return true;
			}
		} catch (SQLException e) {
			request.setAttribute("msgInsertVocabulary", "Không thể kết nối đến database (1)");
		}
		return false;
	}
	private static boolean checkLessionNameDuplicationInForm(ArrayList<String> listLessionName) {
		for(int i = 0; i < listLessionName.size(); i++) {
			for(int j = 0; j < listLessionName.size(); j++) {
				if(i != j && listLessionName.get(i).equals(listLessionName.get(j))) {
					return false;
				}
			}
		}
		return true;
	}
	
	private static String getContent(File file) {
		String content = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			try {
				XWPFDocument document = new XWPFDocument(fis);
				@SuppressWarnings("resource")
				XWPFWordExtractor wordExtractor = new XWPFWordExtractor(document);
				content = wordExtractor.getText();	
				wordExtractor.close();
				document.close();
				fis.close();
				file.delete();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}
	
	public static boolean insertVocabularyLession(HttpServletRequest request) {
		List<Lession> listLession = new ArrayList<>();
		listLession = getLession(request);
		if(listLession == null) {
			return false;
		}
		for(int i = 0; i < listLession.size(); i++) {
			String sql = "insert into vocabulary_lession values(?,?,?)";
			try {
				PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
				statement.setString(1, listLession.get(i).getLessionName());
				statement.setString(2, listLession.get(i).getContent());
				statement.setString(3, listLession.get(i).getDateToday());
				statement.executeUpdate();
			}
			catch(SQLException e) {
				request.setAttribute("msgInsertVocabulary", "Không thể kết nối đến database (2)");
				return false;
			}
		}
		return true;
	}
}
