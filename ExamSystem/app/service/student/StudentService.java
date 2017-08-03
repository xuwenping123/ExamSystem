package service.student;

import java.util.List;

import models.user.Student;

/**
 * 
  * @ClassName: StudentService
  * @Description: Student类的CRUD等操作
  * @author:xuwenping
  * @date: 2017年7月26日
 */
public class StudentService {

	private static StudentService instance = new StudentService();
	
	public static StudentService getInstance() {
		return instance;
	}
	
	public Student addStudent(String name, Integer age, String username,
			String password) {
		Student student = new Student(name, age, username, password);
		student.save();
		return student;
	}
	
	public List<Student> findAll() {
		return Student.findAll();
	}
	
	public Student findStudent(String username, String password) {
		return Student.find("username = ? and password = ?", username, password).first();
	}
	
	public Student findStudent(Long id) {
		return Student.findById(id);
	}
	
	public Student editStudent(Long id, String name, Integer age, String username, String password) {
		Student student = Student.findById(id);
		student.setName(name);
		student.setAge(age);
		student.setUsername(username);
		student.setPassword(password);
		student.save();
		return student;
	}
}
