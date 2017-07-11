package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
@Table(name="t_user")
public class User extends Model{
	
	@Required
	@Column(name="t_name")
	public String name;
	
	@Required
	@Column(name="t_pwd")
	public String password;
	
	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	public User() {
	}
}
