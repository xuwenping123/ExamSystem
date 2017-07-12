package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
@Table(name="t_subject")
public class Subject extends Model{
	
	@Required
	public String title;
	
	public String remark;
	
	@Required
	public int status;
	
	public Subject() {
	}
	
	public Subject(String title, String remark, int status) {
		this.title = title;
		this.remark = remark;
		this.status = status;
	}
	
	public Subject(String title, int status) {
		this.title = title;
		this.status = status;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
