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
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import BEAN.Exercise;
import DB.Connect;

public class UpdateReadingExerciseDAO {
	public static boolean checkExistsExercise(String exerciseName) {
		String sql = "select * from reading_exercise where exerciseName = ?";
		try {
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			statement.setString(1, exerciseName);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				return true;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean updateReadingExercise(HttpServletRequest request) throws UnsupportedEncodingException {	
		String exerciseNameOld = "";
		String exerciseNameNew = "";
		List<Exercise> listExercise = new ArrayList<>();
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
					if(item.getFieldName().equals("exercise-old")) {
						exerciseNameOld = item.getString("UTF-8");
					}
					else if(item.getFieldName().equals("exercise-new")) {
						if(checkExerciseNameNew(item.getString("UTF-8"), exerciseNameOld)) {
							exerciseNameNew = item.getString("UTF-8");
						}
						else {
							request.setAttribute("msgUpdateReading", "Tên bài cần sửa trùng với tên bài trong database");
							return false;
						}
					}
				}
				else {
					File uploadFile = new File("E:" + File.separator + "TestUploadFile" + File.separator + item.getName());
					try {
						item.write(uploadFile);
						
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
	}
	
	private static boolean checkExerciseNameNew(String exerciseNameNew, String exerciseNameOld) {
		if(exerciseNameNew.equals(exerciseNameOld)) {
			return true;
		}
		else {
			String sql = "select exerciseName from reading_exercise where exerciseName = ?";
			try {
				PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
				statement.setString(1, exerciseNameNew);
				ResultSet result = statement.executeQuery();
				if(result.next()) {
					return true;
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			return false;
		}
	}
	
	private static List<Exercise> getContent(List<Exercise> listExercise, File file, String exerciseNameNew) {
		try {
			FileInputStream fis = new FileInputStream(file);
			try {
				Workbook wb = WorkbookFactory.create(fis);
				Sheet sheet = wb.getSheetAt(0);
				Iterator<Row> iterator = sheet.iterator();
				while(iterator.hasNext()) {
					Exercise exercise = new Exercise();
					Row row = iterator.next();
					exercise.setExerciseName(exerciseNameNew);
					
					exercise.setQuestionID(row.getCell(0).getStringCellValue());
					exercise.setQuestionContent(row.getCell(1).getStringCellValue());
					exercise.setOptionA(row.getCell(2).getStringCellValue());
					exercise.setOptionB(row.getCell(3).getStringCellValue());
					exercise.setOptionC(row.getCell(4).getStringCellValue());
					exercise.setOptionD(row.getCell(5).getStringCellValue());
					exercise.setResult(row.getCell(6).getStringCellValue());
					exercise.setDate();
					listExercise.add(exercise);
				}
			} catch (EncryptedDocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listExercise;
	}
}
