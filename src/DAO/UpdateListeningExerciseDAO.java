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

import BEAN.ListeningExercise;
import DB.Connect;

public class UpdateListeningExerciseDAO {
	public static void updateListeningExerciseDatabase(HttpServletRequest request, ArrayList<ListeningExercise> list) throws NumberFormatException, UnsupportedEncodingException {
		if(list != null) {
			@SuppressWarnings("unused")
			boolean check = false;
			for(int i = 0; i < list.size(); i++) {
				ListeningExercise exercise = list.get(i);
				String sql = "update listening_exercise"
						+ " set exerciseName = '" + exercise.getExerciseName() + "'" + ","
						+ " questionID = '" + exercise.getQuestionID() + "'" + ","
						+ " questionContent = '" + exercise.getQuestionContent() + "'" + ","
						+ " optionA = '" + exercise.getOptionA() + "'" + ","
						+ " optionB = '" + exercise.getOptionB() + "'" + ","
						+ " optionC = '" + exercise.getOptionC() + "'" + ","
						+ " optionD = '" + exercise.getOptionD() + "'" + ","
						+ " result = '" + exercise.getResult() + "'" + ","
						+ " date = '" + exercise.getDate() + "'" + ","
						+ " path = '" + exercise.getPath() + "'"
						+ " where exerciseID = " + exercise.getExerciseID();
				try {
					PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
					if(statement.executeUpdate() > 0) {
						check = true;
					}			
				}
				catch(SQLException e) {
					check = false;
					request.setAttribute("msgUpdateDatabase", "Update thất bại(1)");
				}
			}
			if(check) {
				request.setAttribute("msgUpdateListeningExercise", "Update thành công(2)");
			}		
		}
		else {
			request.setAttribute("msgUpdateListeningExercise", "Update thất bại(3)");
		}
	}
	public static ArrayList<ListeningExercise> getListExerciseUpdate(HttpServletRequest request) throws NumberFormatException, UnsupportedEncodingException {
		ArrayList<Integer> listExerciseID = new ArrayList<>();
		ArrayList<String> listExerciseName = new ArrayList<>();
		ArrayList<String> listQuestionID = new ArrayList<>();
		ArrayList<String> listQuestionContent = new ArrayList<>();
		ArrayList<String> listOptionA = new ArrayList<>();
		ArrayList<String> listOptionB = new ArrayList<>();
		ArrayList<String> listOptionC = new ArrayList<>();
		ArrayList<String> listOptionD = new ArrayList<>();
		ArrayList<String> listResult = new ArrayList<>();
		ArrayList<String> listDate = new ArrayList<>();
		ArrayList<String> listPath = new ArrayList<>();
		ArrayList<String> listFileName = new ArrayList<>();
		
		ArrayList<ListeningExercise> list = new ArrayList<>();
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
					if(item.getFieldName().equals("exerciseID")) {
						int id = Integer.parseInt(item.getString("UTF-8"));
						listExerciseID.add(id);
					}
					else if(item.getFieldName().equals("exerciseName")) {
						listExerciseName.add(item.getString("UTF-8"));
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
					else if(item.getFieldName().equals("date")) {
						listDate.add(item.getString("UTF-8"));
					}
					else if(item.getFieldName().equals("path")) {
						listPath.add(item.getString("UTF-8"));
					}
				}
				else {
					if(!checkFileName(item.getName()) && !item.getName().equals("")) {
						request.setAttribute("msgCheckFileName", "File upload không có định dạng .mp3, vui lòng kiểm tra lại");
						return null;
					}
					if(item.getName().equals("")) {
						listFileName.add(item.getName());
					}
					else {
						String fileName = Math.abs(new Random().nextInt()) + new Date().getTime() + item.getName();
						File file = new File("E:" + File.separator + "TestUploadFile" + File.separator + fileName);
						try {
							listFileName.add(fileName);
							item.write(file);
						} catch (Exception e) {
							request.setAttribute("msgWriteFile", "Không thể ghi file");
						}
					}
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < listExerciseID.size(); i++) {
			ListeningExercise exercise = new ListeningExercise();
			exercise.setExerciseID(listExerciseID.get(i));
			exercise.setExerciseName(listExerciseName.get(i));
			exercise.setQuestionID(listQuestionID.get(i));
			exercise.setQuestionContent(listQuestionContent.get(i));
			exercise.setOptionA(listOptionA.get(i));
			exercise.setOptionB(listOptionB.get(i));
			exercise.setOptionC(listOptionC.get(i));
			exercise.setOptionD(listOptionD.get(i));
			exercise.setResult(listResult.get(i));
			exercise.setDate(listDate.get(i));
			if(!listFileName.get(i).equals("")) {
				String fileName = listPath.get(i);
				File file = new File("E:" + File.separator + "TestUploadFile" + File.separator + fileName);
				if(file.exists()) {
					file.delete();
				}
				exercise.setPath(listFileName.get(i));
			}
			else {
				exercise.setPath(listPath.get(i));
			}
			list.add(exercise);
		}
		return list;
	}
	private static boolean checkFileName(String fileName) {
		Pattern pattern = Pattern.compile(".+\\.mp3");
		Matcher matcher = pattern.matcher(fileName);
		return matcher.matches();
	}
}
