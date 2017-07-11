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
	
	public Subject() {
	}
	
	public Subject(String title, String remark) {
		this.title = title;
		this.remark = remark;
	}
	
	public Subject(String title) {
		this.title = title;
	}
}
