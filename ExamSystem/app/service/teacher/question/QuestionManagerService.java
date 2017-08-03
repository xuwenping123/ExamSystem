package service.teacher.question;

import java.util.List;
import models.Question;
import util.ConstantUtil;
import util.PageUtil;
import vo.teacher.question.QuestionListPageVO;

/**
 * 
  * @ClassName: QuestionManagerService
  * @Description: 试题管理service 
  * @author:xuwenping
  * @date: 2017年7月31日
 */
public class QuestionManagerService {

	private static QuestionManagerService instance = new QuestionManagerService();
	
	public static QuestionManagerService getInstance() {
		return instance;
	}
	
	public int getQuestionCount() {
		return (int)Question.count();
	}
	
	public List<Question> findAll() {
		return Question.findAll();
	}
	
	public Question findQuestion(Long id) {
		return Question.findById(id);
	}
	
	public QuestionListPageVO go2QuestionListPage() {
		return selectPage(1);
	}
	
	public Question addQuestion(String content, String answer, Integer type, Long subject_id) {
		Question question = new Question(content, answer, type, subject_id);
		question.save();
		return question;
	}
	
	public Question editQuestion(Long id, String content, String answer, Integer type, Long subject_id) {
		Question question = Question.findById(id);
		question.setContent(content);
		question.setAnswer(answer);
		question.setType(type);
		question.setSubject_id(subject_id);
		question.save();
		return question;
	}
	
	public Question deleteQuestion(Long id) {
		Question question = Question.findById(id);
		question.delete();
		return question;
	}
	
	public int getTotalPage(Integer questionCount) {
		return PageUtil.getInstance().getTotalPage(questionCount);
	}
	
	public QuestionListPageVO selectPage(Integer selectPage) {
		List<Question> questions = Question.all().fetch(selectPage, ConstantUtil.perPage);
		int totalPage = getTotalPage(getQuestionCount());
		return new QuestionListPageVO(questions, totalPage, selectPage);
	}
	
	public QuestionListPageVO previousPage(Integer currentPage) {
		if (currentPage.equals(1)) {
			return selectPage(currentPage);
		}
		return selectPage(--currentPage);
	}
	
	public QuestionListPageVO nextPage(Integer currentPage) {
		if (currentPage.equals(getTotalPage(getQuestionCount()))) {
			return selectPage(currentPage);
		}
		return selectPage(++currentPage);
	}
}
