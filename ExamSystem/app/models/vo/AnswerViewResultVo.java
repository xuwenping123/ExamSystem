package models.vo;

import models.Paper;
import models.Question;
import models.user.Student;

/**
 * 前端考生用于查看考试结果的vo	
 * 不包括 学生考试所得整分，只有每题所得分
 * @author think
 *
 */
public class AnswerViewResultVo {

	private Student student;
	
	private Paper paper;
	
	private Question question;
	
	private String result;
	
	private int score;

	public AnswerViewResultVo(Student student, Paper paper, Question question, String result, int score) {
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
