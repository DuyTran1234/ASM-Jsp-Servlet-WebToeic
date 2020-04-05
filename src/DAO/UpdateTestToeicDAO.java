package DAO;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import BEAN.TestToeic;
import DB.Connect;

public class UpdateTestToeicDAO {
	public static void deleteTestToeic(int id) {
		String sql = "delete from test_toeic where id = ?";
		try {
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void insertTestToeic(HttpServletRequest request, String testToeicName) throws UnsupportedEncodingException {
		ArrayList<TestToeic> listTestToeic = new ArrayList<>();
		ArrayList<String> listQuestionID = new ArrayList<>();
		ArrayList<String> listQuestionContent = new ArrayList<>();
		ArrayList<String> listOptionA = new ArrayList<>();
		ArrayList<String> listOptionB = new ArrayList<>();
		ArrayList<String> listOptionC = new ArrayList<>();
		ArrayList<String> listOptionD = new ArrayList<>();
		ArrayList<String> listResult = new ArrayList<>();
		ArrayList<String> listPath = new ArrayList<>();
		
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
					if(item.getFieldName().equals("questionID")) {
						listQuestionID.add(item.getString("UTF-8"));
					}
					else if(item.getFieldName().equals("questionContent")) {
						listQuestionContent.add(item.getString("UTF-8"));
					}
					else if(item.getFieldName().equals("optionA")) {
						listOptionA.add(item.getString("UTF-8"));
					}
					else if(item.getFieldName().equals("optionB")) {
						listOptionB.add(item.getString("UTF-8"));
					}
					else if(item.getFieldName().equals("optionC")) {
						listOptionC.add(item.getString("UTF-8"));
					}
					else if(item.getFieldName().equals("optionD")) {
						listOptionD.add(item.getString("UTF-8"));
					}
					else if(item.getFieldName().equals("result")) {
						listResult.add(item.getString("UTF-8"));
					}
				}
				else {
					Pattern pattern = Pattern.compile(".+\\.mp3");
					Matcher matcher = pattern.matcher(item.getName());
					if(!matcher.matches() && !item.getName().equals("")) {
						request.setAttribute("msgPattern", "File đã chọn không có định dạng mp3");
						return;
					}
					if(item.getName().equals("")) {
						listPath.add(item.getName());
					}
					else {
						String fileName = Math.abs(new Random().nextInt()) + new Date().getTime() + item.getName();
						File file = new File("E:" + File.separator + "TestUploadFile" + File.separator + fileName);
						try {
							item.write(file);
							listPath.add(fileName);
						} catch (Exception e) {
							request.setAttribute("msgFileWrite", "Không thể ghi file, vui lòng thử lại");
							return;
						}
					}
				}
			}
		} catch (FileUploadException e) {
			request.setAttribute("msgFileUpload", "Không thể upload đề");
			return;
		}
		for(int i = 0; i < listQuestionID.size(); i++) {
			TestToeic toeic = new TestToeic();
			toeic.setQuestionID(listQuestionID.get(i));
			toeic.setQuestionContent(listQuestionContent.get(i));
			toeic.setOptionA(listOptionA.get(i));
			toeic.setOptionB(listOptionB.get(i));
			toeic.setOptionC(listOptionC.get(i));
			toeic.setOptionD(listOptionD.get(i));
			toeic.setResult(listResult.get(i));
			toeic.setDate();
			toeic.setPath(listPath.get(i));
			listTestToeic.add(toeic);
		}
		if(listTestToeic.size() > 0) {
			for(int i = 0; i < listTestToeic.size(); i++) {
				TestToeic toeic = listTestToeic.get(i);
				String sql = "insert into test_toeic(testToeicName,questionID, questionContent,optionA,optionB,optionC,optionD,result,date,path) values(?,?,?,?,?,?,?,?,?,?)";
				try {
					PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
					statement.setString(1, testToeicName);
					statement.setString(2, toeic.getQuestionID());
					statement.setString(3, toeic.getQuestionContent());
					statement.setString(4, toeic.getOptionA());
					statement.setString(5, toeic.getOptionB());
					statement.setString(6, toeic.getOptionC());
					statement.setString(7, toeic.getOptionD());
					statement.setString(8, toeic.getResult());
					statement.setString(9, toeic.getDate());
					statement.setString(10, toeic.getPath());
					statement.executeUpdate();
				} catch (SQLException e) {
					request.setAttribute("msgDB", "Truy xuất database lỗi");
					return;
				}
			}
			request.setAttribute("msgInsert", "Update thành công");
		}
		else {
			request.setAttribute("msgInsert", "Chưa thêm dữ liệu");
			return;
		}
	}
	public static void deleteAllTestToeic(String testToeicName) {
		String sql = "delete from test_toeic where testToeicName = ?";
		try {
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			statement.setString(1, testToeicName);
			statement.executeUpdate();
		}
		catch(SQLException e) {
			return;
		}
	}
	public static void editTestToeic(HttpServletRequest request) throws NumberFormatException, UnsupportedEncodingException {
		List<TestToeic> listTestToeic = new ArrayList<>();
		List<Integer> listId = new ArrayList<>();
		List<String> listTestToeicName = new ArrayList<>();
		List<String> listQuestionID = new ArrayList<>();
		List<String> listQuestionContent = new ArrayList<>();
		List<String> listOptionA = new ArrayList<>();
		List<String> listOptionB = new ArrayList<>();
		List<String> listOptionC = new ArrayList<>();
		List<String> listOptionD = new ArrayList<>();
		List<String> listResult = new ArrayList<>();
		List<String> listPath = new ArrayList<>();
		List<String> listFileName = new ArrayList<>();
		
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
					if(item.getFieldName().equals("id")) {
						listId.add(Integer.parseInt(item.getString("UTF-8")));
					}
					else if(item.getFieldName().equals("testToeicName")) {
						listTestToeicName.add(item.getString("UTF-8"));
					}
					else if(item.getFieldName().equals("questionID")) {
						listQuestionID.add(item.getString("UTF-8"));
					}
					else if(item.getFieldName().equals("questionContent")) {
						listQuestionContent.add(item.getString("UTF-8"));
					}
					else if(item.getFieldName().equals("optionA")) {
						listOptionA.add(item.getString("UTF-8"));
					}
					else if(item.getFieldName().equals("optionB")) {
						listOptionB.add(item.getString("UTF-8"));
					}
					else if(item.getFieldName().equals("optionC")) {
						listOptionC.add(item.getString("UTF-8"));
					}
					else if(item.getFieldName().equals("optionD")) {
						listOptionD.add(item.getString("UTF-8"));
					}
					else if(item.getFieldName().equals("result")) {
						listResult.add(item.getString("UTF-8"));
					}
					else if(item.getFieldName().equals("path")) {
						listPath.add(item.getString("UTF-8"));
					}
				}
				else {
					Pattern pattern = Pattern.compile(".+\\.mp3");
					Matcher matcher = pattern.matcher(item.getName());
					if(!matcher.matches() && !item.getName().equals("")) {
						request.setAttribute("msgPatternEdit", "File đã chọn không có định dạng mp3");
						return;
					}
					if(item.getName().equals("")) {
						listFileName.add(item.getName());
					}
					else {
						String fileName = Math.abs(new Random().nextInt()) + new Date().getTime() + item.getName();
						File file = new File("E:" + File.separator + "TestUploadFile" + File.separator + fileName);
						try {
							item.write(file);
							listFileName.add(fileName);
						} catch (Exception e) {
							request.setAttribute("msgFileWriteEdit", "Không thể ghi file");
							return;
						}
					}
				}
			}
		} catch (FileUploadException e) {
			request.setAttribute("msgFileUploadEdit", "Không thể upload file");
			return;
		}
		for(int i = 0; i < listId.size(); i++) {
			TestToeic toeic = new TestToeic();
			toeic.setId(listId.get(i));
			toeic.setTestToeicName(listTestToeicName.get(i));
			toeic.setQuestionID(listQuestionID.get(i));
			toeic.setQuestionContent(listQuestionContent.get(i));
			toeic.setOptionA(listOptionA.get(i));
			toeic.setOptionB(listOptionB.get(i));
			toeic.setOptionC(listOptionC.get(i));
			toeic.setOptionD(listOptionD.get(i));
			toeic.setResult(listResult.get(i));
			if(!listFileName.get(i).equals("")) {
				toeic.setPath(listFileName.get(i));
			}
			else {
				toeic.setPath(listPath.get(i));
			}
			listTestToeic.add(toeic);
		}
		for(int i = 0; i < listTestToeic.size(); i++) {
			TestToeic toeic = listTestToeic.get(i);
			String sql = "update test_toeic set testToeicName = ?, questionID = ?, questionContent = ?, optionA = ?, optionB = ?, optionC = ?, optionD = ?, result = ?, path = ? where id = ?";
			try {
				PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
				statement.setString(1, toeic.getTestToeicName());
				statement.setString(2, toeic.getQuestionID());
				statement.setString(3, toeic.getQuestionContent());
				statement.setString(4, toeic.getOptionA());
				statement.setString(5, toeic.getOptionB());
				statement.setString(6, toeic.getOptionC());
				statement.setString(7, toeic.getOptionD());
				statement.setString(8, toeic.getResult());
				statement.setString(9, toeic.getPath());
				statement.setInt(10, toeic.getId());
				statement.executeUpdate();
			}
			catch(SQLException e) {
				request.setAttribute("msgDatabaseEdit", "Sửa đề thi thất bại");
				return;
			}
		}
		request.setAttribute("msgDatabaseEdit", "Sửa đề thi thành công");
	}
}



