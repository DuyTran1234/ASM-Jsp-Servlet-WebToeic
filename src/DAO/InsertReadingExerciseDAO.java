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
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import BEAN.Exercise;
import DB.Connect;

public class InsertReadingExerciseDAO {
	public static boolean insertReadingExercise(HttpServletRequest request) {
		try {
			List<Exercise> listExercise = getData(request);
			for(int i = 0; i < listExercise.size(); i++) {
				String sql = "insert into reading_exercise (exerciseName,questionID,questionContent,optionA,optionB,optionC,optionD,result,date) values(?,?,?,?,?,?,?,?,?)";
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
					statement.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("msgInsertReadingExercise", "Upload thành công");
			}
			return true;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private static List<Exercise> getData(HttpServletRequest request) throws UnsupportedEncodingException {
		@SuppressWarnings("unused")
		String exerciseName = null;
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
					Pattern pattern = Pattern.compile("lessionID.+");
					Matcher matcher = pattern.matcher(item.getFieldName());
					if(matcher.matches()) {
						exerciseName = item.getString("UTF-8");
					}
				}
				else {				
					if(exerciseName != null && checkFileType(item.getName())) {
						File uploadFile = new File("E:" + File.separator + "TestUploadFile" + File.separator + item.getName());
						try {
							item.write(uploadFile);
							listExercise = getContent(listExercise, exerciseName, uploadFile);
							uploadFile.delete();
						} catch (Exception e) {
							request.setAttribute("msgInsertReadingExercise", "Không thể ghi File");
						}
					}
					else {
						request.setAttribute("msgInsertReadingExercise", "File không có định dạng .xls hoặc .xlsx");
					}
				}
			}
		} catch (FileUploadException e) {
			request.setAttribute("msgInsertReadingExercise", "Không thể xử lý request");
		}
		
		
		return listExercise;
	}
	
	private static List<Exercise> getContent(List<Exercise> listExercise, String exerciseName, File file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			try {
				Workbook wb = WorkbookFactory.create(fis);
				Sheet sheet = wb.getSheetAt(0);
				Iterator<Row> iterator = sheet.iterator();
				while(iterator.hasNext()) {
					Row row = iterator.next();
					Exercise exercise = new Exercise();
					exercise.setExerciseName(exerciseName);				
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
	
	private static boolean checkFileType(String fileName) {
		Pattern pattern = Pattern.compile(".+\\.xls|.+\\.xlsx");
		Matcher matcher = pattern.matcher(fileName);
		return matcher.matches();
	}
	
}
