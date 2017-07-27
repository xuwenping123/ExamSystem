package service.teacher;

import models.user.Teacher;

/**
 * 
  * @ClassName: TeacherService
  * @Description: teacher CRUD操作
  * @author:xuwenping
  * @date: 2017年7月26日
  * @version V1.0
 */
public class TeacherService {

	private static TeacherService instance = new TeacherService();
	
	public static TeacherService getInstance() {
		return instance;
	}
	
	public Teacher addTeacher(String name, Integer age, String username, String password) {
		Teacher teacher = new Teacher(name, age, username, password);
		teacher.save();
		return teacher;
	}
	
	public Teacher findTeacher(String username, String password) {
		return Teacher.find("username = ? and password = ?", username, password).first();
	}
	
	public Teacher findTeacher(Long id) {
		return Teacher.findById(id);
	}
	
	public Teacher editTeacher(Long id, String name, Integer age, String username, String password) {
		Teacher teacher = Teacher.findById(id);
		teacher.setName(username);
		teacher.setAge(age);
		teacher.setUsername(username);
		teacher.setPassword(password);
		teacher.save();
		return teacher;
	}
}
