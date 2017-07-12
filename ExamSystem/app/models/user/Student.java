package models.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
@Table(name="t_student")
public class Student extends Model{

	@Required
	public String name;
	
	public int age;
	
	@Required
	public String username;
	
	@Required
	public String password;
	
	public Student() {
	}
	
	public Student(String name, int age, String username, String password) {
		this.name = name;
		this.age = age;
		this.username = username;
		this.password = password;
	}
	
	public Student(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
