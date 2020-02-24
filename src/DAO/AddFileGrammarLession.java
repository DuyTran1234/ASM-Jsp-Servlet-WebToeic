package DAO;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class AddFileGrammarLession {
	public static boolean addFile(HttpServletRequest request) {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(2 * 1024 * 1024);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> iterator = items.iterator();
			
			//File uploadFile = new File("WebToeic" + File.separator + "WebContent" + File.separator + "Document" + File.separator + "GrammarLession" + File.separator + fileName);
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean checkFileExists(Iterator<FileItem> iterator, HttpServletRequest request) {
		while(iterator.hasNext()) {
			FileItem item = iterator.next();
			if(!item.isFormField()) {
				String fileName = item.getName();
				
				File uploadFile = new File("WebToeic" + File.separator + "WebContent" + File.separator + "Document" + File.separator + "GrammarLession" + File.separator + fileName);
				if(uploadFile.exists()) {
					return false;
				}
			}
		}
		return true;
	}
}	
