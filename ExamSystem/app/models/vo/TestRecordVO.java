package models.vo;

import java.util.Date;
import java.util.List;

import models.Paper;
import models.TestDetail;
import models.TestRecord;
import models.user.Student;
import models.user.Teacher;
import play.data.validation.Required;

public class TestRecordVO {
	
	private TestRecord testRecord;

	private TestDetail testDetail;
	
	private Paper paper;
	
	private Teacher teacher;
	
	private List<Student> students;

	public TestDetail getTestDetail() {
		return testDetail;
	}

	public void setTestDetail(TestDetail testDetail) {
		this.testDetail = testDetail;
	}

	public TestRecord getTestRecord() {
		return testRecord;
	}

	public void setTestRecord(TestRecord testRecord) {
		this.testRecord = testRecord;
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

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public TestRecordVO(TestRecord testRecord, TestDetail testDetail, Paper paper, Teacher teacher,
			List<Student> students) {
		this.testRecord = testRecord;
		this.testDetail = testDetail;
		this.paper = paper;
		this.teacher = teacher;
		this.students = students;
	}

}
