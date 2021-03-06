package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import models.user.Student;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
@Table(name="t_answer_record")
public class AnswerRecord extends Model{
	
	@Required
	public long student_id;
	
	@Required
	public long paper_id;
	
	@Required
	public long testRecord_id;
	
	@Required
	public long question_id;
	
	public String result;
	
	/** student_id 单题所得分  */
	public int score;

	/**
	 * 提交必填参数
	 * @param student_id
	 * @param paper_id
	 * @param testRecord_id
	 * @param question_id
	 * @param result
	 */
	public AnswerRecord(long student_id, long paper_id, long testRecord_id, long question_id, String result) {
		this.student_id = student_id;
		this.paper_id = paper_id;
		this.testRecord_id = testRecord_id;
		this.question_id = question_id;
		this.result = result;
	}
	
	public AnswerRecord(long student_id, long paper_id, long testRecord_id, long question_id, String result,
			int score) {
		this.student_id = student_id;
		this.paper_id = paper_id;
		this.testRecord_id = testRecord_id;
		this.question_id = question_id;
		this.result = result;
		this.score = score;
	}



	public void setStudent_id(long student_id) {
		this.student_id = student_id;
	}

	public void setPaper_id(long paper_id) {
		this.paper_id = paper_id;
	}

	public void setTestRecord_id(long testRecord_id) {
		this.testRecord_id = testRecord_id;
	}

	public void setQuestion_id(long question_id) {
		this.question_id = question_id;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public long getStudent_id() {
		return student_id;
	}

	public long getPaper_id() {
		return paper_id;
	}

	public long getTestRecord_id() {
		return testRecord_id;
	}

	public long getQuestion_id() {
		return question_id;
	}

	public String getResult() {
		return result;
	}

	public int getScore() {
		return score;
	}

}
