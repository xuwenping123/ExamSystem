package vo.student.view;

import models.Paper;
import models.Question;
import models.user.Student;

/**
 * 
  * @ClassName: ViewTestPageVO
  * @Description: 考试答题卡页面
  * @author:xuwenping
  * @date: 2017年8月2日
 */
public class ViewTestPageVO {
	
	private Student student;
	
	private Paper paper;
	
	private Question question;
	
	private String result;

	private int score;

	public ViewTestPageVO(Student student, Paper paper, Question question, String result, int score) {
		this.student = student;
		this.paper = paper;
		this.question = question;
		this.result = result;
		this.score = score;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}
