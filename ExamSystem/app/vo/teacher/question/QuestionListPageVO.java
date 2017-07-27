package vo.teacher.question;

import java.util.List;
import models.Question;

public class QuestionListPageVO {

	private List<Question> questions;
	
	private int totalPage;
	
	private int currentPage;

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public QuestionListPageVO(List<Question> questions, int totalPage, int currentPage) {
		this.questions = questions;
		this.totalPage = totalPage;
		this.currentPage = currentPage;
	}
	
}
