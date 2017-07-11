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
	
	@Required
	public long student_id;
	
	@Required
	public long paper_id;
	
	@Required
	public long testRecord_id;
	
	@Required
	public long question_id;
	
	public String result;
	
	public int score;

}
