package BEAN;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Exercise {
	private Integer exerciseID = null;
	private String exerciseName = null;
	private String questionID = null;
	private String questionContent = null;
	private String optionA = null;
	private String optionB = null;
	private String optionC = null;
	private String optionD = null;
	private String result = null;
	private String date = null;
	private String exerciseNameOld = null;
	
	public String getExerciseNameOld() {
		return exerciseNameOld;
	}
	public void setExerciseNameOld(String exerciseNameOld) {
		this.exerciseNameOld = exerciseNameOld;
	}
	
	public String getDate() {
		return this.date;
	}
	public void setDate() {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		this.date = simpleDateFormat.format(date);
	}
	public void setDate(String date) {
		this.date = date;
	}

	public Integer getExerciseID() {
		return exerciseID;
	}
	public void setExerciseID(Integer exerciseID) {
		this.exerciseID = exerciseID;
	}
	public String getExerciseName() {
		return exerciseName;
	}
	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}
	public String getQuestionID() {
		return questionID;
	}
	public void setQuestionID(String questionId) {
		this.questionID = questionId;
	}
	public String getQuestionContent() {
		return questionContent;
	}
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
	public String getOptionA() {
		return optionA;
	}
	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}
	public String getOptionB() {
		return optionB;
	}
	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}
	public String getOptionC() {
		return optionC;
	}
	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}
	public String getOptionD() {
		return optionD;
	}
	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
}
