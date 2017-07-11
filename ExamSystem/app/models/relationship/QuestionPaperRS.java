package models.relationship;

import javax.persistence.Entity;
import javax.persistence.Table;

import models.Paper;
import models.Question;
import play.db.jpa.Model;

@Entity
@Table(name="t_question_paper")
public class QuestionPaperRS extends Model{

	public Paper paper;
	public Question question;
	
	public QuestionPaperRS(Paper paper, Question question) {
		this.paper = paper;
		this.question = question;
	}
}
