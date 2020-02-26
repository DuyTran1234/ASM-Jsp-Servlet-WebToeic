package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
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
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import BEAN.Lession;
import DB.Connect;

public class AddFileGrammarLession {
	public static List<File> addFile(HttpServletRequest request, List<String> listLessionName) {
		
		List<File> listFile = new ArrayList<>();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(2 * 1024 * 1024);	// 2MB
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(50 * 1024 * 1024);
		try {
			List<FileItem> items = upload.parseRequest(request);
			if(!checkDuplicateFile(items, request)) {
				return null;
			}
			Iterator<FileItem> iterator = items.iterator();
			while(iterator.hasNext()) {
				FileItem item = iterator.next();
				if(!item.isFormField()) {
					if(!checkPatternFile(item.getName(), request)) {
						return null;
					}
					File uploadFile = new File("E:" + File.separator + "TestUploadFile" + File.separator + item.getName());
					if(uploadFile.exists()) {
						request.setAttribute("msgUploadFile", "File đã tồn tại, vui lòng chọn lại");
						return null;
					}
					else {
						try {
							item.write(uploadFile);
							listFile.add(uploadFile);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				else {
					String lessionName;
					try {
						lessionName = item.getString("UTF-8");
						listLessionName.add(lessionName);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listFile;
	}
	private static boolean checkPatternFile(String fileName, HttpServletRequest request) {
		Pattern pattern = Pattern.compile(".+\\.docx");
		Matcher matcher = pattern.matcher(fileName);
		if(!matcher.matches()) {
			request.setAttribute("msgUploadFile", "File đã chọn không có định dạng docx, vui lòng chọn lại");
			return false;
		}
		return true;
	}
	private static boolean checkDuplicateFile(List<FileItem> items, HttpServletRequest request) {
		if(items.size() > 2) {
			for(int i = 0; i < items.size(); i++) {
				for(int j = 0; j < items.size(); j++) {
					if(!items.get(i).isFormField() && !items.get(j).isFormField() && i != j) {
						if(items.get(i).getName().equals(items.get(j).getName())) {
							request.setAttribute("msgUploadFile", "Các file đã chọn đã trùng lặp, vui lòng chọn lại");
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	public static String getContentFile(File file) {
		String content = "";
		try {
			FileInputStream fis = new FileInputStream(file);
			try {
				XWPFDocument document = new XWPFDocument(fis);
				@SuppressWarnings("resource")
				XWPFWordExtractor extractor = new XWPFWordExtractor(document);
				content = extractor.getText();
				extractor.close();
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
	
	public static boolean insertLession(Lession lession) {
		String sql = "insert into grammar_lession values(?,?,?)";
		try {
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			statement.setString(1, lession.getLessionName());
			statement.setString(2, lession.getContent());
			statement.setString(3, lession.getDateToday());
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}	












