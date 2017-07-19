package service;

import java.util.ArrayList;
import java.util.List;

import models.Paper;
import models.Question;
import models.relationship.QuestionPaperRS;

public class QuestionPaperRsService {

	private final static QuestionPaperRsService instance = new QuestionPaperRsService();
	
	public static QuestionPaperRsService getInstance() {
		return instance;
	}
	
	/**
	 * 通过paper_id 获取当前试卷的总分
	 * 进行了非空的判断，返回0
	 * @param paper_id
	 * @return
	 */
	public int getTotalScoreByPaperId(long paper_id) {
		String query = "select sum(question_score) from QuestionPaperRS where paper_id = ?";
		List<Long> list = QuestionPaperRS.find(query, paper_id).fetch();
		if (list == null || list.size() == 0) {
			return 0;
		}
		if (list.get(0) == null) {
			return 0;
		}
		long totalScroe = list.get(0);
		return (int)totalScroe;
	}
	
	/**
	 * 通过试卷paper_id 获取相关联的试题（包括每道试题的分数）
	 * 
	 * @param paper_id
	 * @return
	 */
	public List<Question> getQuestionAndScoreByPaperId(long paper_id) {
		List<QuestionPaperRS> questionPaperRSs = QuestionPaperRS.find("paper_id = ?", paper_id).fetch();
		List<Question> questions = new ArrayList<Question>();
		QuestionPaperRS questionPaperRS;
		Question question = null;
		for (int i = 0; i < questionPaperRSs.size(); i++) {
			questionPaperRS = questionPaperRSs.get(i);
			question = Question.findById(questionPaperRS.getQuestion_id());
			question.setQuestion_score(questionPaperRS.getQuestion_score());
			questions.add(question);
		}
		return questions;
	}
	
	/**
	 * 通过paper_id 获取可以添加至该试卷的所有试题
	 * @param paper_id
	 * @return
	 */
	public List<Question> getQuestionsByPaperId(long paper_id) {
		Paper paper = Paper.findById(paper_id);
		return Question.find("subject_id = ?", paper.subject_id).fetch();
	}
}
