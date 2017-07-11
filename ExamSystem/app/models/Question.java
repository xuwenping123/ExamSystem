package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
@Table(name="t_question")
public class Question extends Model{

	@Required
	@Column(name="content")
	public String content;
	
	@Required
	@Column(name="answer")
	public String answer;
	
	@Required
	@Column(name="type")
	public int type;
	
	public Subject subject;
	
	public Question() {
	}
	
	public Question(String content, String answer, int type, Subject subject) {
		this.content = content;
		this.answer = answer;
		this.type = type;
		this.subject = subject;
	}
}
