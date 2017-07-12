package controllers;

import models.user.Student;
import play.data.validation.Required;
import play.mvc.Controller;
/**
 * 学生类 
 * @author think
 *
 */
public class StudentController extends Controller{

	/**
	@Required
	public String name;
	
	public int age;
	
	@Required
	public String username;
	
	@Required
	public String password;
	*/
	public static void testAddStudent() {
		Student student = new Student("xuwenping", 18, "xuwenping", "123");
		student.save();
		student = new Student("changsha", 22, "changsha", "changsha123");		
		student.save();
	}
	
	/**
	 * 添加student用户	http.post	所有信息
	 */
	public static void addStudent() {
		String name = params.get("name");
		int age = Integer.valueOf(params.get("age"));
		String username = params.get("username");
		String password = params.get("password");
		Student student = new Student(name, age, username, password);
		student.save();
		renderArgs.put("student", student);
		render("student/student.html");
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
	public static void modifyStudent() {
		long id = Long.valueOf(params.get("id"));
		String name = params.get("name");
		int age = Integer.valueOf(params.get("age"));
		String username = params.get("username");
		String password = params.get("password");
		Student student = Student.findById(id);
		student.setName(name);
		student.setAge(age);
		student.setUsername(username);
		student.setPassword(password);
		student.save();
		renderArgs.put("student", student);
		render("student/student.html");
	}
}
