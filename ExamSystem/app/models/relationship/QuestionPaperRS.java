package models.relationship;

import javax.persistence.Entity;
import javax.persistence.Table;

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
	
	public QuestionPaperRS(long paper_id, long question_id) {
		this.paper_id = paper_id;
		this.question_id = question_id;
	}
}
