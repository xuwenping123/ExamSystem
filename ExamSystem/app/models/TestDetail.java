package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import models.user.Student;
import models.user.Teacher;
import play.db.jpa.Model;

@Entity
@Table(name="t_testdetail")
public class TestDetail extends Model{

	public TestRecord testRecord;
	
	public Teacher teacher;
	
	public Student student;
	
	@Column(name="totalScore")
	public int totalScore;
}
