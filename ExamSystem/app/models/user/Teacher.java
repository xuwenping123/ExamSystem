package models.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
@Table(name="t_teacher")
public class Teacher extends Model{

	@Required
	public String name;
	
	public int age;
	
	public Teacher() {
	}
	
	public Teacher(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public Teacher(String name) {
		this.name = name;
	}
}
