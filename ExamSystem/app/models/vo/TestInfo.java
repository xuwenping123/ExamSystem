package models.vo;

import java.util.Date;

import models.Paper;
import models.Subject;
import models.TestDetail;
import models.TestRecord;
import models.user.Student;
import models.user.Teacher;

public class TestInfo {
	
	private Paper paper;
	
	private Teacher teacher;
	
	private Student student;
	
	private Subject subject;
	
	private TestRecord testRecord;
	
	private TestDetail testDetail;
	
	public TestInfo(Paper paper, Teacher teacher, Student student, Subject subject, TestRecord testRecord,
			TestDetail testDetail) {
		super();
		this.paper = paper;
		this.teacher = teacher;
		this.student = student;
		this.subject = subject;
		this.testRecord = testRecord;
		this.testDetail = testDetail;
	}

	public TestRecord getTestRecord() {
		return testRecord;
	}

	public void setTestRecord(TestRecord testRecord) {
		this.testRecord = testRecord;
	}

	public TestDetail getTestDetail() {
		return testDetail;
	}

	public void setTestDetail(TestDetail testDetail) {
		this.testDetail = testDetail;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

}
