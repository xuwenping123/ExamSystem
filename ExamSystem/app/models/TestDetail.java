package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import models.user.Student;
import models.user.Teacher;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
@Table(name="t_testdetail")
public class TestDetail extends Model{

	@Required
	public long testRecord_id;
	
	@Required
	public long teacher_id;
	
	@Required
	public long student_id;
	
	/** student_id 在testRecord_id 中所得总分 */
	public int totalScore;

	public TestDetail() {
	}

	public TestDetail(long testRecord_id, long teacher_id, long student_id) {
		this.testRecord_id = testRecord_id;
		this.teacher_id = teacher_id;
		this.student_id = student_id;
	}
	
	public void setTestRecord_id(long testRecord_id) {
		this.testRecord_id = testRecord_id;
	}

	public void setTeacher_id(long teacher_id) {
		this.teacher_id = teacher_id;
	}

	public void setStudent_id(long student_id) {
		this.student_id = student_id;
	}

}
