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
	
	public Student student;
	
	public Paper paper;
	
	public TestRecord testRecord;
	
	public Question question;
	
	@Column(name="result")
	public String result;
	
	@Column(name="score")
	public int score;
}
