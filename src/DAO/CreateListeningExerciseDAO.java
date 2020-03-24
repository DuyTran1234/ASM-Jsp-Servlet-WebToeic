package DAO;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

import BEAN.ListeningExercise;
import DB.Connect;

public class CreateListeningExerciseDAO {
	public static boolean checkExistsExercise(String exerciseName) {
		String sql = "select exerciseName from listening_exercise where exerciseName = ?";
		try {
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			statement.setString(1, exerciseName);
			ResultSet result = statement.executeQuery();
			return result.next();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	@SuppressWarnings("unused")
	public static boolean insertListeningExercise(HttpServletRequest request) {
		try {
			List<ListeningExercise> listExercise = createListeningExercise(request);
			if(listExercise == null) {
				request.setAttribute("msgCreateListening", "Tạo thất bại, vui lòng kiểm tra lại file mp3");
				return false;
			}		
			if(listExercise.size() == 0) {
				request.setAttribute("msgCreateListening", "Tạo bài tập thất bại, chưa thêm câu hỏi");
				return false;
			}
			if(checkExistsExercise(listExercise.get(0).getExerciseName())) {
				request.setAttribute("msgCreateListening", "Tồn tại tên bài tập trong cơ sở dữ liệu");
				return false;
			}
			for(int i = 0; i < listExercise.size(); i++) {
				String sql = "insert into listening_exercise(exerciseName,questionID,questionContent,optionA,optionB,optionC,optionD,result,date,path) values(?,?,?,?,?,?,?,?,?,?)";
				try {
					PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
					statement.setString(1, listExercise.get(i).getExerciseName());
					statement.setString(2, listExercise.get(i).getQuestionID());
					statement.setString(3, listExercise.get(i).getQuestionContent());
					statement.setString(4, listExercise.get(i).getOptionA());
					statement.setString(5, listExercise.get(i).getOptionB());
					statement.setString(6, listExercise.get(i).getOptionC());
					statement.setString(7, listExercise.get(i).getOptionD());
					statement.setString(8, listExercise.get(i).getResult());
					statement.setString(9, listExercise.get(i).getDate());
					statement.setString(10, listExercise.get(i).getPath());
					statement.executeUpdate();
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
			request.setAttribute("msgCreateListening", "Tạo bài tập thành công");
			return true;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("msgCreateListening", "Tạo bài tập thất bại");
		return false;
	}
	public static List<ListeningExercise> createListeningExercise(HttpServletRequest request) throws UnsupportedEncodingException {	
		List<ListeningExercise> listExercise = new ArrayList<>();
		String exerciseName = null;
		List<String> listQuestionId = new ArrayList<>();
		List<String> listQuestionContent = new ArrayList<>();
		List<String> listOptionA = new ArrayList<>();
		List<String> listOptionB = new ArrayList<>();
		List<String> listOptionC = new ArrayList<>();
		List<String> listOptionD = new ArrayList<>();
		List<String> listResult = new ArrayList<>();
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
				if(item.getFieldName().equals("exercise-listening-name")) {
					exerciseName = item.getString("UTF-8");
				}
				if(item.isFormField()) {						
					if(item.getFieldName().equals("question-id")) {
						listQuestionId.add(item.getString("UTF-8"));
					}
					else if(item.getFieldName().equals("question-content")) {
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
					if(!checkFileType(item.getName())) {
						return null;
					}
					String path = "E:" + File.separator + "TestUploadFile";
					String fileName = Math.abs(new Random().nextInt()) + new Date().getTime() + item.getName();
					File file = new File(path + File.separator + fileName);
					listFileName.add(fileName);
					try {
						item.write(file);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		for(int i = 0; i < listQuestionId.size(); i++) {
			ListeningExercise exercise = new ListeningExercise();
			exercise.setExerciseName(exerciseName);
			exercise.setQuestionID(listQuestionId.get(i));
			exercise.setQuestionContent(listQuestionContent.get(i));
			exercise.setOptionA(listOptionA.get(i));
			exercise.setOptionB(listOptionB.get(i));
			exercise.setOptionC(listOptionC.get(i));
			exercise.setOptionD(listOptionD.get(i));
			exercise.setResult(listResult.get(i));
			exercise.setDate();
			exercise.setPath(listFileName.get(i));
			listExercise.add(exercise);
		}
		return listExercise;
	}
	private static boolean checkFileType(String fileName) {
		Pattern pattern = Pattern.compile(".+\\.mp3");
		Matcher matcher = pattern.matcher(fileName);
		return matcher.matches();
	}
}
