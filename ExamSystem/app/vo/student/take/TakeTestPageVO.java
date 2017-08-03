package vo.student.take;

import java.util.List;

import models.Question;

public class TakeTestPageVO {

	private long student_id;

	private long testRecord_id;
	
	private List<Question> questions;

	public TakeTestPageVO(long student_id, long testRecord_id, List<Question> questions) {
		this.student_id = student_id;
		this.testRecord_id = testRecord_id;
		this.questions = questions;
	}

	public long getStudent_id() {
		return student_id;
	}

	public void setStudent_id(long student_id) {
		this.student_id = student_id;
	}

	public long getTestRecord_id() {
		return testRecord_id;
	}

	public void setTestRecord_id(long testRecord_id) {
		this.testRecord_id = testRecord_id;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

}
