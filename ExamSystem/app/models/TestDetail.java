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
	
	/** 标记位，确定该试卷考试是否参加考试  */
	@Required
	public int istaked;
	
	/** 标记位， 确定该试卷是否被老师阅卷 */
	@Required
	public int isMarked;

	public TestDetail(long testRecord_id, long teacher_id, long student_id, int istaked, int isMarked) {
		this.testRecord_id = testRecord_id;
		this.teacher_id = teacher_id;
		this.student_id = student_id;
		this.istaked = istaked;
		this.isMarked = isMarked;
	}

	public int getIstaked() {
		return istaked;
	}

	public void setIstaked(int istaked) {
		this.istaked = istaked;
	}

	public int getIsMarked() {
		return isMarked;
	}

	public void setIsMarked(int isMarked) {
		this.isMarked = isMarked;
	}

	public long getTestRecord_id() {
		return testRecord_id;
	}

	public void setTestRecord_id(long testRecord_id) {
		this.testRecord_id = testRecord_id;
	}

	public long getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(long teacher_id) {
		this.teacher_id = teacher_id;
	}

	public long getStudent_id() {
		return student_id;
	}

	public void setStudent_id(long student_id) {
		this.student_id = student_id;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	
}
