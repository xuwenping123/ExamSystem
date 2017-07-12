package models.relationship;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.eclipse.jdt.core.IInitializer;

import models.Paper;
import models.Question;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
@Table(name="t_question_paper")
public class QuestionPaperRS extends Model{

	@Required
	public long paper_id;
	
	@Required
	public long question_id;
	
	public int question_score;
	
	public QuestionPaperRS(long paper_id, long question_id, int question_score) {
		this.paper_id = paper_id;
		this.question_id = question_id;
		this.question_score = question_score;
	}

	public void setPaper_id(long paper_id) {
		this.paper_id = paper_id;
	}

	public void setQuestion_id(long question_id) {
		this.question_id = question_id;
	}

	public void setQuestion_score(int question_score) {
		this.question_score = question_score;
	}

	public long getPaper_id() {
		return paper_id;
	}

	public long getQuestion_id() {
		return question_id;
	}

	public int getQuestion_score() {
		return question_score;
	}
}
