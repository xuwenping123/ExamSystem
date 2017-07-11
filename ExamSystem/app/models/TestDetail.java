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
	
	public int totalScore;
}
