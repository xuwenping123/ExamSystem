package controllers;

import models.AnswerRecord;
import play.data.validation.Required;
import play.mvc.Controller;

/**
 * 答题记录表 -> AnswerRecordService
 * @author think
 *
 */
public class AnswerRecordController extends Controller{

	/**
	 * @Required
	public long student_id;
	
	@Required
	public long paper_id;
	
	@Required
	public long testRecord_id;
	
	@Required
	public long question_id;
	
	public String result;
	
	public int score;
	 */
	/**
	 * 添加答题记录一条
	 * @param student_id
	 * @param paper_id
	 * @param testRecord_id
	 * @param question_id
	 * @param result
	 */
	public static void addAnswerRecord(long student_id, long paper_id, long testRecord_id, long question_id, String result){
		AnswerRecord answerRecord = new AnswerRecord(student_id, paper_id, testRecord_id, question_id, result);
		answerRecord.save();
		renderArgs.put("answerRecord", answerRecord);
		render("answerRecord/answerRecord.html");
	}
	
	/**
	 * 查询答题记录 
	 * @param id
	 */
	public static void findAnswerRecord(long id) {
		AnswerRecord answerRecord = AnswerRecord.findById(id);
		renderArgs.put("answerRecord", answerRecord);
		render("answerRecord/answerRecord.html");		
	}
	
	/**
	 * 查询一个学生一场考试中的同一张试卷上的所有试题
	 * @param student_id
	 * @param paper_id
	 */
	public static void findStudentPaperQuestions(long student_id,long testRecord_id, long paper_id) {
		
	}
}
