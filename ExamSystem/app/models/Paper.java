package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
@Table(name="t_paper")
public class Paper extends Model{

	@Required
	public String remark;
	
	@Required
	public long subject_id;
	
	@Transient
	public int totalScore;
	
	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public Paper(String remark, long subject_id) {
		this.remark = remark;
		this.subject_id = subject_id;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setSubject_id(long subject_id) {
		this.subject_id = subject_id;
	}
	
}
