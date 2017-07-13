package controllers;

import java.util.List;

import models.Question;
import models.TestDetail;
import models.relationship.QuestionPaperRS;
import play.Logger;
import play.mvc.Controller;

public class QuestionPaperRsController extends Controller{

	/**
	 * 对试题与试卷进行绑定  http.get all
	 * @param paper_id
	 * @param question_id
	 */
	public static void bindQuestionPaper() {
		long paper_id = Long.valueOf(params.get("paper_id"));
		long question_id = Long.valueOf(params.get("question_id"));
		int question_score = Integer.valueOf(params.get("question_score"));
		QuestionPaperRS questionPaperRS = new QuestionPaperRS(paper_id, question_id, question_score);
		questionPaperRS.save();
	}
	
	/**
	 * 对试题与试卷进行解绑 http.get paper_id question_id
	 */
	public static void unBindQuestionPaper(long paper_id, long question_id) {
		List<QuestionPaperRS> questionPaperRSs = QuestionPaperRS.find("paper_id = ? and question_id = ?", paper_id, question_id).fetch();
		QuestionPaperRS questionPaperRS = questionPaperRSs.get(0);
		questionPaperRS.delete();
	}
	
	/**
	 * 获取单张试卷总分
	 * @param paper_id http.get
	 */
	public static void getAllScore(long paper_id) {
		String query = "select sum(question_score) from QuestionPaperRS where paper_id = ?";
		long totalScore = (long)QuestionPaperRS.find(query, paper_id).fetch().get(0);
		renderArgs.put("totalScore", totalScore);
		Logger.info("试卷总分：%s\t 试卷Id: %s", totalScore, paper_id);
	}
}
