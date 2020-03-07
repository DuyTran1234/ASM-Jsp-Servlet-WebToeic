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
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import BEAN.Lession;
import DB.Connect;

public class UpdateVocabularyLessionDAO {
	public static boolean checkExistsLessionName(String lessionNameOld) {
		String sql = "select * from vocabulary_lession where lessionName = ?";
		try {
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			statement.setString(1, lessionNameOld);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean updateVocabularyLession(HttpServletRequest request) {
		Lession lession = getLession(request);
		if(lession.getContent() == null) {
			String sql = "update vocabulary_lession set lessionName = ? where lessionName = ?";		
			
			try {
				PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
				statement.setString(1, lession.getLessionName());
				statement.setString(2, lession.getLessionNameOld());
				statement.executeUpdate();
				return true;
			} catch (SQLException e) {
				return false;
			}				
		}
		else {
			String sql = "update vocabulary_lession set lessionName = ?, content = ? where lessionName = ?";
			try {
				PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
				statement.setString(1, lession.getLessionName());
				statement.setString(2, lession.getContent());
				statement.setString(3, lession.getLessionNameOld());
				statement.executeUpdate();
				return true;
			} catch (SQLException e) {
				return false;
			}
		}
	}
	
	public static Lession getLession(HttpServletRequest request) {
		Lession lession = new Lession();
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(2 * 1024 * 1024);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(50 * 1024 * 1024);
		
		try {
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> iterator = items.iterator();
			while(iterator.hasNext()) {
				FileItem item = iterator.next();
				if(item.isFormField()) {
					String valueField = "";
					try {
						valueField = item.getString("UTF-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(item.getFieldName().equals("lession-name-old")) {
						lession.setLessionNameOld(valueField);
					}
					else if(item.getFieldName().equals("lession-name-new")) {
						if(valueField.equals(lession.getLessionNameOld())) {
							lession.setLessionName(valueField);
						}
						else {
							if(checkLessionNameInDatabase(valueField)) {
								request.setAttribute("msgUpdateVocabulary", "Tên bài học cần sửa trùng với tên trong cơ sở dữ liệu");
								return null;
							}
							else {
								lession.setLessionName(valueField);
							}
						}
					}
				}
				else {
					File uploadFile = new File("E:" + File.separator + "TestUploadFile" + File.separator + item.getName());
					try {
						item.write(uploadFile);
						lession.setContent(getContent(uploadFile));
						uploadFile.delete();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lession;
	}

	private static boolean checkLessionNameInDatabase(String lessionNameNew) {
		String sql = "select * from vocabulary_lession where lessionName = ?";
		try {
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			statement.setString(1, lessionNameNew);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	private static String getContent(File file) {
		String content = "";
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
}
