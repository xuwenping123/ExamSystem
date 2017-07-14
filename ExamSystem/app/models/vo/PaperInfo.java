package models.vo;

import java.util.List;

import models.Paper;
import models.Question;
import models.relationship.QuestionPaperRS;
import models.user.Student;

public class PaperInfo {
	
	private Paper paper;
	/** 考试学生， 单个个体 */
	private Student student;
	
	public PaperInfo(Paper paper, Student student) {
		super();
		this.paper = paper;
		this.student = student;
	}
	
	public Paper getPaper() {
		return paper;
	}
	public void setPaper(Paper paper) {
		this.paper = paper;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}

}
