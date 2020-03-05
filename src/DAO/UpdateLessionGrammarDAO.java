package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

public class UpdateLessionGrammarDAO {
	public static boolean updateLessionGrammar(Lession lession, String lessionNameOld) {
		String sql = "update grammar_lession set lessionName = ? , content = ? where lessionName = ?";
		try {
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			statement.setString(1, lession.getLessionName());
			statement.setString(2, lession.getContent());
			statement.setString(3, lessionNameOld);
			statement.executeUpdate();
			return true;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	public static boolean updateLessionGrammar(String lessionNameNew, String lessionNameOld) {
		String sql = "update grammar_lession set lessionName = ? where lessionName = ?";
		try {
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			statement.setString(1, lessionNameNew);
			statement.setString(2, lessionNameOld);
			statement.executeUpdate();
			return true;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	public static Lession getData(HttpServletRequest request, Lession lession) {
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
					if(item.getFieldName().equals("lession-name-old")) {
						try {
							lession.setLessionNameOld(item.getString("UTF-8"));
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else if(item.getFieldName().equals("lession-name-update")) {
						try {
							lession.setLessionName(item.getString("UTF-8"));
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
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
			return lession;
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private static String getContent(File file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			try {
				XWPFDocument document = new XWPFDocument(fis);
				@SuppressWarnings("resource")
				XWPFWordExtractor wordExtractor = new XWPFWordExtractor(document);
				return wordExtractor.getText();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}







