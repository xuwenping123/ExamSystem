package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="t_paper")
public class Paper extends Model{

	@Column(name="remark")
	public String remark;
	
	public Subject subject;
	
	public Paper() {
	}
	
	public Paper(String remark, Subject subject) {
		this.remark = remark;
		this.subject = subject;
	}
	
	public Paper(Subject subject) {
		this.subject = subject;
	}
}
