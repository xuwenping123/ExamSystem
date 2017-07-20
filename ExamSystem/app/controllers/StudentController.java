package controllers;

import com.sun.org.apache.xml.internal.security.Init;

import models.user.Student;
import play.Logger;
import play.mvc.Controller;

/**
 * 学生类
 * 
 * @author think
 *
 */
public class StudentController extends Controller {

	/**
	 * 进入编辑学生信息页面
	 * @param id 学生id
	 */
	public static void editStudent(long id) {
		Student student = Student.findById(id);
		render("users/editStudent.html", student);
	}
	
	/**
	 * 添加student用户 http.post 所有信息
	 */
	public static void addStudent(String name, Integer age, String username, String password) {
		Student student = new Student(name, age, username, password);
		student.save();
		renderArgs.put("student", student);
		render("users/student.html");
	}

	/**
	 * 删除student http.get id
	 */
	public static void deleteStudent(long id) {
		Student student = Student.findById(id);
		student.delete();
		renderArgs.put("student", student);
		render("student/student.html");
	}

	/**
	 * 修改student http.post all
	 */
	public static void modifyStudent(Long id, String name, int age, String username, String password) {
		Student student = Student.findById(id);
		student.setName(name);
		student.setAge(age);
		student.setUsername(username);
		student.setPassword(password);
		student.save();
		Logger.info("modify student info name=%s\t age=%s\t username=%s\t", student.name, student.age, student.username);
		renderArgs.put("student", student);
		render("users/student.html");
	}
}
