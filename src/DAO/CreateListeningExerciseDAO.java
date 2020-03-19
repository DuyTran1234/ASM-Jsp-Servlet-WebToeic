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
				if(item.isFormField()) {
					if(item.getFieldName().equals("exercise-listening-name")) {
						exerciseName = item.getString("UTF-8");
						if(checkExistsExercise(exerciseName)) {
							request.setAttribute("msgCreateListening", "Tồn tại tên bài tập trong database");
							return null;
						}
					}
					else if(item.getFieldName().equals("question-id")) {
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
					String path = "E:" + File.separator + "TestUploadFile";
					String fileName = item.getName() + Math.abs(new Random().nextInt()) + new Date().getTime();
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
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
}
