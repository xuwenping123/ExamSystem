package models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
@Table(name="t_question")
public class Question extends Model{

	@Required
	public String content;
	
	@Required
	public String answer;
	
	@Required
	public int type;
	
	@Required
	public long subject_id;
	
	@Transient
	public int question_score;
	
	public int getQuestion_score() {
		return question_score;
	}

	public void setQuestion_score(int question_score) {
		this.question_score = question_score;
	}

	public Question(String content, String answer, int type, long subject_id) {
		this.content = content;
		this.answer = answer;
		this.type = type;
		this.subject_id = subject_id;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setSubject_id(long subject_id) {
		this.subject_id = subject_id;
	}
}
