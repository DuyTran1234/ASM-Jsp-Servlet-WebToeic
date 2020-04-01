package DAO;

import java.io.File;
import java.io.UnsupportedEncodingException;
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

public class CreateTestToeicDAO {
	public static void insertTestToeicDatabase(HttpServletRequest request) throws UnsupportedEncodingException {
		List<TestToeic> list = getList(request);
		if(list == null) {
			return;
		}
		else {
			
		}
	}
	
	private static List<TestToeic> getList(HttpServletRequest request) throws UnsupportedEncodingException {
		List<TestToeic> listTestToeic = new ArrayList<>();
		String testToeicName = null;
		List<String> listQuestionID = new ArrayList<>();
		List<String> listQuestionContent = new ArrayList<>();
		List<String> listOptionA = new ArrayList<>();
		List<String> listOptionB = new ArrayList<>();
		List<String> listOptionC = new ArrayList<>();
		List<String> listOptionD = new ArrayList<>();
		List<String> listResult = new ArrayList<>();
		List<String> listPath = new ArrayList<>();
		
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
					if(item.getFieldName().equals("test-toeic-name-upload")) {
						testToeicName = item.getString("UTF-8");
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
					if(!matcher.matches()) {
						request.setAttribute("msgPatternFile", "File không có định dạng mp3, kiểm tra lại danh sách");
						return null;
					}
					String fileName = Math.abs(new Random().nextInt()) + new Date().getTime() + item.getName();
					File file = new File("E:" + File.separator + "TestUploadFile" + File.separator + fileName);
					try {
						item.write(file);
						listPath.add(fileName);
					} catch (Exception e) {
						request.setAttribute("msgWriteFile", "Không thể ghi File");
						return null;
					}
				}
			}
		} catch (FileUploadException e) {
			request.setAttribute("msgFileUpload", "Không thể xử lý yêu cầu upload file");
			return null;
		}
		for(int i = 0; i < listQuestionID.size(); i++) {
			TestToeic toeic = new TestToeic();
			toeic.setTestToeicName(testToeicName);
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
		return listTestToeic;
	}
}	
