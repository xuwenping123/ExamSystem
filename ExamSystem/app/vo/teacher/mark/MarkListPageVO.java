package vo.teacher.mark;

import models.Paper;
import models.TestRecord;
import models.user.Student;
import models.user.Teacher;

public class MarkListPageVO {

private Student student;
	
	private Paper paper;
	
	private TestRecord testRecord;
	/** 试卷满分 */
	private int totalScore;
	
	private Teacher teacher;

	public MarkListPageVO(Student student, Paper paper, TestRecord testRecord, int totalScore, Teacher teacher) {
		this.student = student;
		this.paper = paper;
		this.testRecord = testRecord;
		this.totalScore = totalScore;
		this.teacher = teacher;
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

	public TestRecord getTestRecord() {
		return testRecord;
	}

	public void setTestRecord(TestRecord testRecord) {
		this.testRecord = testRecord;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
}
