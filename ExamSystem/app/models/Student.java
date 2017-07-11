package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
@Table(name="t_student")
public class Student extends Model{

	@Required
	@Column(name="name")
	public String name;
	
	@Column(name="age")
	public int age;
	
	public Student() {
		
	}
}
